package com.example.tutorialsproject.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MediaUtils {
    private static final String TAG = MediaUtils.class.getSimpleName();

    /**
     * Get runtime duration of media such as audio or video in milliseconds
     **/
    public static long getDuration(Context ctx, Uri mediaUri) {
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{MediaStore.Video.Media.DURATION}, null, null, null);
        long duration = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    duration = cur.getLong(cur.getColumnIndex(MediaStore.Video.Media.DURATION));

                    if (duration == 0)
                        Log.w(TAG, "#getMediaDuration The image size was found to be 0. Reason: UNKNOWN");

                }    // end while
            } else if (cur.getCount() == 0) {
                Log.e(TAG, "#getMediaDuration cur size is 0. File may not exist");
            } else {
                Log.e(TAG, "#getMediaDuration cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return duration;
    }

    /**
     * Checks if the parameter {@link android.net.Uri} is a Media content uri.
     **/
    public static boolean isMediaContentUri(Uri uri) {
        if (!uri.toString().contains("content://media/")) {
            Log.w(TAG, "#isContentUri The uri is not a media content uri");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get the file path from the Media Content Uri for video, audio or images.
     **/
    public static String getPathForMediaUri(Context context, Uri mediaContentUri) {

        Cursor cur = null;
        String path = null;

        try {
            String[] projection = {MediaStore.MediaColumns.DATA};
            cur = context.getContentResolver().query(mediaContentUri, projection, null, null, null);

            if (cur != null && cur.getCount() != 0) {
                cur.moveToFirst();
                path = cur.getString(cur.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            }

            // Log.v( TAG, "#getRealPathFromURI Path: " + path );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cur != null && !cur.isClosed())
                cur.close();
        }

        return path;
    }

    /**
     * Creates an intent to take a video from camera or gallery or any other application that can
     * handle the intent.
     */
    public static Intent createTakeVideoIntent(Activity ctx, Uri savingUri, int durationInSeconds) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, durationInSeconds);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("video/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    /**
     * Creates a ACTION_IMAGE_CAPTURE photo & ACTION_GET_CONTENT intent. This intent will be
     * aggregation of intents required to take picture from Gallery and Camera at the minimum. The
     * intent will also be directed towards the apps that are capable of sourcing the image data.
     * For e.g. Dropbox, Astro file manager.
     *
     * @param savingUri Uri to store a high resolution image at. If the user takes the picture using the
     *                  camera the image will be stored at this uri.
     **/
    public static Intent createTakePictureIntent(Activity ctx, Uri savingUri) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    /**
     * Creates external content:// scheme uri to save the videos at.
     **/
    @Nullable
    public static Uri createVideoUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }


    /**
     * Gets media type from the Uri.
     */
    @Nullable
    public static String getMediaType(Uri uri) {
        if (uri == null) {
            return null;
        }

        String uriStr = uri.toString();

        if (uriStr.contains("video")) {
            return "video";
        } else if (uriStr.contains("audio")) {
            return "audio";
        } else if (uriStr.contains("image")) {
            return "image";
        } else {
            return null;
        }
    }

    /**
     * Gets media file name.
     **/
    public static String getMediaFileName(Context ctx, Uri mediaUri) {
        String colName = MediaStore.MediaColumns.DISPLAY_NAME;
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{colName}, null, null, null);
        String dispName = null;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    dispName = cur.getString(cur.getColumnIndex(colName));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getMediaFileName byte.size: " + size );

                    if (TextUtils.isEmpty(colName)) {
                        Log.w(TAG, "#getMediaFileName The file name is blank or null. Reason: UNKNOWN");
                    }

                } // end while
            } else if (cur != null && cur.getCount() == 0) {
                Log.e(TAG, "#getMediaFileName File may not exist");
            } else {
                Log.e(TAG, "#getMediaFileName cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return dispName;
    }

    /**
     * Gets the size of the media resource pointed to by the parameter mediaUri.
     * <p/>
     * Known bug: for unknown reason, the image size for some images was found to be 0
     *
     * @param mediaUri uri to the media resource. For e.g. content://media/external/images/media/45490 or
     *                 content://media/external/video/media/45490
     * @return Size in bytes
     **/
    public static long getMediaSize(Context context, Uri mediaUri) {
        Cursor cur = context.getContentResolver().query(mediaUri, new String[]{MediaStore.Images.Media.SIZE}, null, null, null);
        long size = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    size = cur.getLong(cur.getColumnIndex(MediaStore.Images.Media.SIZE));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getSize byte.size: " + size );

                    if (size == 0)
                        Log.w(TAG, "#getSize The media size was found to be 0. Reason: UNKNOWN");

                } // end while
            } else if (cur.getCount() == 0) {
                Log.e(TAG, "#getMediaSize cur size is 0. File may not exist");
            } else {
                Log.e(TAG, "#getMediaSize cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return size;
    }

    /**
     * Returns true if the mime type is a standard audio mime type
     */
    public static boolean isAudio(String mimeType) {
        // TODO: apply regex patter for checking the MIME type
        if (mimeType != null) {
            if (mimeType.startsWith("audio/"))
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Returns true if the mime type is a standard video mime type
     */
    public static boolean isVideo(String mimeType) {
        // TODO: apply regex patter for checking the MIME type
        if (mimeType != null) {
            if (mimeType.startsWith("video/"))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    /**
     * Identifies if the content represented by the parameter mimeType is media. Image, Audio and
     * Video is treated as media by this method. You can refer to standard MIME type here. <a
     * href="http://www.iana.org/assignments/media-types/media-types.xhtml" >Standard MIME
     * types.</a>
     *
     * @param mimeType standard MIME type of the data.
     */
    public static boolean isMedia(String mimeType) {
        boolean isMedia = false;

        if (mimeType != null) {
            if (mimeType.startsWith("image/") || mimeType.startsWith("video/") || mimeType.startsWith("audio/")) {
                isMedia = true;
            }
        } else {
            isMedia = false;
        }

        return isMedia;
    }

    /**
     * Get the type of the media. Audio, Video or Image.
     *
     * @return Lower case string for one of above listed media type
     */
    public static String getMediaType(String contentType) {
        if (isMedia(contentType)) {
            if (isVideo(contentType))
                return "video";
            else if (isAudio(contentType))
                return "audio";
            else if (ImageUtils.isImage(contentType))
                return "image";
            else
                return null;
        } else {
            return null;
        }
    }

}
