package com.example.tutorialsproject.util;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ImageUtils {
    private final static String TAG = ImageUtils.class.getSimpleName();
    private static final String ERROR_URI_NULL = "Uri cannot be null";

    //Rotate the image at the specified uri. For the rotation of the image the android.media.ExifInterface data in the image will be used.
    public static Uri rotateImage(Context context, Uri uri) throws FileNotFoundException, IOException {
        // rotate the image
        if (uri == null) {
            throw new NullPointerException(ERROR_URI_NULL);
        }

        if (!MediaUtils.isMediaContentUri(uri)) {
            return null;
        }

        int invalidOrientation = -1;
        byte[] data = getMediaData(context, uri);

        int orientation = getOrientation(context, uri);

        Uri newUri = null;

        try {

            Log.d(TAG, "#rotateImage Exif orientation: " + orientation);

            if (orientation != invalidOrientation) {
                Matrix matrix = new Matrix();

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        matrix.postRotate(90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        matrix.postRotate(180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        matrix.postRotate(270);
                        break;
                }

                // set some options so the memory is manager properly
                BitmapFactory.Options options = new BitmapFactory.Options();
                // options.inPreferredConfig = Bitmap.Config.RGB_565; // try to enable this if
                // OutOfMem issue still persists
                options.inPurgeable = true;
                options.inInputShareable = true;

                Bitmap original = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true); // rotating
                // bitmap
                String newUrl = MediaStore.Images.Media.insertImage(((Activity) context).getContentResolver(), rotatedBitmap, "", "");

                if (newUrl != null) {
                    newUri = Uri.parse(newUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newUri;
    }

    @Nullable
    /**
     * @deprecated This is a monster that will lead to OutOfMemory exception some day and the world
     * will come to an end.
     * Gets the media data from the one of the following media {@link android.content.ContentProvider} This method
     * should not be called from the main thread of the application. Calling this method may have
     * performance issues as this may allocate a huge memory array.
     * @param context Context object
     * @param uri Media content uri of the image, audio or video resource
     */
    public static byte[] getMediaData(Context context, Uri uri) {
        if (uri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        byte[] data = null;

        try {
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                    try {
                        File file = new File(path);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        data = readStreamToBytes(fileInputStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Log.v( TAG, "#getVideoData byte.size: " + data.length );
                } // end while
            } else {
                Log.e(TAG, "#getMediaData cur is null or blank");
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return data;
    }

    /**
     * Convert {@linkplain java.io.InputStream} to byte array.
     *
     * @throws NullPointerException If input parameter {@link java.io.InputStream} is null
     **/
    public static byte[] readStreamToBytes(InputStream inputStream) {

        if (inputStream == null) {
            throw new NullPointerException("InputStream is null");
        }

        byte[] bytesData = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            bytesData = buffer.toByteArray();

            // Log.d( TAG, "#readStream data: " + data );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (reader != null) {
                try {
                    reader.close();

                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    // finally

        return bytesData;
    }

    /**
     * Gets the orientation of the image pointed to by the parameter uri
     *
     * @return Image orientation value corresponding to <code>ExifInterface.ORIENTATION_*</code> <br/>
     * Returns -1 if the row for the {@link android.net.Uri} is not found.
     **/
    public static int getOrientation(Context context, Uri uri) {

        int invalidOrientation = -1;
        if (uri == null) {
            throw new NullPointerException(ERROR_URI_NULL);
        }

        if (!MediaUtils.isMediaContentUri(uri)) {
            return invalidOrientation;
        }

        String filePath = Utils.getPathForMediaUri(context, uri);
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = invalidOrientation;
        if (exif != null) {
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, invalidOrientation);
        }

        return orientation;
    }

}
