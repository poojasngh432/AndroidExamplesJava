package com.example.tutorialsproject.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ImageUtils {
    private final static String TAG = ImageUtils.class.getSimpleName();
    private static final String ERROR_URI_NULL = "Uri cannot be null";

    /**
     * Returns true if the mime type is a standard image mime type
     */
    public static boolean isImage(String mimeType) {
        // TODO: apply regex patter for checking the MIME type
        if (mimeType != null) {
            if (mimeType.startsWith("image/"))
                return true;
            else
                return false;
        } else
            return false;
    }

    public static void showImageUsingGlide(Context context, String imgUrl, ImageView view){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(RandomUtils.getRandomDrawableColor());
        requestOptions.error(RandomUtils.getRandomDrawableColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.fitCenter();

        Glide.with(context)
                .load(imgUrl)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view);
    }

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

    /**
     * Serializes the Bitmap to Base64
     **/
    public static String toBase64(Bitmap bitmap) {

        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }

        String base64Bitmap = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBitmap = stream.toByteArray();
        base64Bitmap = Base64.encodeToString(imageBitmap, Base64.DEFAULT);

        return base64Bitmap;
    }

    /**
     * Converts the passed in drawable to Bitmap representation
     **/
    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable == null) {
            throw new NullPointerException("Drawable to convert should NOT be null");
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        if (drawable.getIntrinsicWidth() <= 0 && drawable.getIntrinsicHeight() <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    /**
     * Creates external content:// scheme uri to save the images at. The image saved at this
     * {@link android.net.Uri} will be visible via the gallery application on the device.
     **/
    @Nullable
    public static Uri createImageUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    /**
     * Converts a given bitmap to byte array
     */
    public static byte[] toBytes(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * Resizes an image to the given width and height parameters Prefer using
     */
    public static Bitmap resizeImage(Bitmap sourceBitmap, int newWidth, int newHeight, boolean filter) {
        if (sourceBitmap == null) {
            throw new NullPointerException("Bitmap to be resized cannot be null");
        }

        Bitmap resized = null;

        if (sourceBitmap.getWidth() < sourceBitmap.getHeight()) {
            // image is portrait
            resized = Bitmap.createScaledBitmap(sourceBitmap, newHeight, newWidth, true);
        } else {
            // image is landscape
            resized = Bitmap.createScaledBitmap(sourceBitmap, newWidth, newHeight, true);
        }

        resized = Bitmap.createScaledBitmap(sourceBitmap, newWidth, newHeight, true);

        return resized;
    }

    /**
     * @param compressionFactor Powers of 2 are often faster/easier for the decoder to honor
     */
    public static Bitmap compressImage(Bitmap sourceBitmap, int compressionFactor) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opts.inSampleSize = compressionFactor;

        if (Build.VERSION.SDK_INT >= 10) {
            opts.inPreferQualityOverSpeed = true;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        sourceBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opts);

        return image;
    }

    /**
     * Provide the height to which the sourceImage is to be resized. This method will calculate the
     * resultant height. Use scaleDownBitmap from {@link Utils} wherever possible
     */
    public Bitmap resizeImageByHeight(int height, Bitmap sourceImage) {
        int widthO = 0; // original width
        int heightO = 0; // original height
        int widthNew = 0;
        int heightNew = 0;

        widthO = sourceImage.getWidth();
        heightO = sourceImage.getHeight();
        heightNew = height;

        // Maintain the aspect ratio
        // of the original banner image.
        widthNew = (heightNew * widthO) / heightO;

        return Bitmap.createScaledBitmap(sourceImage, widthNew, heightNew, true);
    }

    /**
     * Inserts an image into {@link android.provider.MediaStore.Images.Media} content provider of the device.
     *
     * @return The media content Uri to the newly created image, or null if the image failed to be
     * stored for any reason.
     **/
    public static String writeImageToMedia(Context ctx, Bitmap image, String title, String description) {
        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        return MediaStore.Images.Media.insertImage(ctx.getContentResolver(), image, title, description);
    }

    /**
     * Writes the given image to the external storage of the device. If external storage is not
     * available, the image is written to the application private directory
     *
     * @return Path of the image file that has been written.
     **/
    public static String writeImage(Context ctx, byte[] imageData) {

        final String FILE_NAME = "photograph.jpeg";
        File dir = null;
        String filePath = null;
        OutputStream imageFileOS;

        dir = Utils.getStorageDirectory(ctx, null);

        // dir.mkdirs();
        File f = new File(dir, FILE_NAME);

        // File f = getFile( FILE_NAME );

        try {
            imageFileOS = new FileOutputStream(f);
            imageFileOS.write(imageData);
            imageFileOS.flush();
            imageFileOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        filePath = f.getAbsolutePath();

        return filePath;
    }

    /**
     * @deprecated This is a monster that will lead to OutOfMemory exception some day and the world
     * will come to an end.
     * Gets the media data from the one of the following media {@link android.content.ContentProvider} This method
     * should not be called from the main thread of the application. Calling this method may have
     * performance issues as this may allocate a huge memory array.
     * @param context Context object
     * @param uri Media content uri of the image, audio or video resource
     **/
    @Nullable
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

        String filePath = MediaUtils.getPathForMediaUri(context, uri);
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
