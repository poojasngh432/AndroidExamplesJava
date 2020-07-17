package com.example.imagesproject;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;


public class TakePhotoActivity extends BaseActivity implements SurfaceHolder.Callback, View.OnKeyListener {
    private static final int ORIENTATION_PORTRAIT_NORMAL = 1;
    private static final int ORIENTATION_PORTRAIT_INVERTED = 2;
    private static final int ORIENTATION_LANDSCAPE_NORMAL = 3;
    private static final int ORIENTATION_LANDSCAPE_INVERTED = 4;
    private final Camera.ShutterCallback myShutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
        }
    };
    private final String TAG = getClass().getSimpleName();
    private ImageView switchCameraImage;
    private RelativeLayout paintView;
    private View takePicView;
    private int canvasWidth = 600;
    private int canvasHeight = 800;
    private DrawingPanel drawView;
    private int lastColor = 0xFFFF0000;
    private SavePhotoTask savePhotoTask;
    private SaveEditPhotoTask saveEditPhotoTask;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean previewing = false;
    private final Camera.PictureCallback myPictureCallback_JPG = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] arg0, Camera arg1) {
            if (camera != null) {
                camera.stopPreview();
                camera.release();
            }
            previewing = false;
            savePhotoTask = new SavePhotoTask(arg0);
            savePhotoTask.execute();
        }
    };
    private LayoutInflater controlInflater = null;
    private Bitmap bitmapPicture;
    private int currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private RelativeLayout acceptCancelSelfie;
    private ProgressBar progressBar;
    private boolean isFlashOn;
    private OrientationEventListener mOrientationEventListener;
    private int mOrientation = -1;
    private int orientation = -1;
    private Camera.PictureCallback myPictureCallback_raw = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {

//            savePhotoTask = new SavePhotoTask(bytes);
//            savePhotoTask.execute();

        }
    };
    private Uri imagePath;
    private int highlightImage;
    private ImageView flashLightImageView;
    private boolean imageCaptured;
    private View cameraControls;
    private String textToDraw = null;
    private boolean isTextModeOn = false;
    private Canvas transparentCanvas;
    private Bitmap transparentBitmap;

    private void setCameraDisplayOrientation(Activity activity, int cameraId, final android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }
        try {
            camera.setDisplayOrientation(result);
        } catch (NullPointerException ex) {
            Toast.makeText(this, getString(R.string.camera_error_message), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        // Get tracker.
        getSupportActionBar().setTitle(R.string.take_photo);
//        Tracker t = ((Gac) getApplication()).getTracker(TrackerName.APP_TRACKER);
//        Gac.sendGaData(t, this.getClass().getCanonicalName());

        imagePath = getIntent().getParcelableExtra(MediaStore.EXTRA_OUTPUT);
        highlightImage = getIntent().getIntExtra("HIGHLIGHT_IMAGE", 0);
        int isFront = getIntent().getIntExtra("isFront", 0);
        if (isFront == 1) {
            boolean test = checkFrontCameraExists();
            if (checkFrontCameraExists()) {
                currentCameraId = CAMERA_FACING_FRONT;
            }
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        getWindow().setFormat(ImageFormat.JPEG);
        surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        controlInflater = LayoutInflater.from(getBaseContext());
        final View viewControl = controlInflater.inflate(R.layout.custom_camera_view, null);
        RelativeLayout.LayoutParams layoutParamsControl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        this.addContentView(viewControl, layoutParamsControl);

        cameraControls = viewControl.findViewById(R.id.camera_controls);
        acceptCancelSelfie = (RelativeLayout) viewControl.findViewById(R.id.accept_cancel_selfie_layout);
        progressBar = (ProgressBar) viewControl.findViewById(R.id.saving_image_bar);
        paintView = (RelativeLayout) viewControl.findViewById(R.id.paint_view);

        takePicView = viewControl.findViewById(R.id.take_pic_layout);
        try {
            takePicView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orientation = mOrientation;
                    progressBar.setVisibility(View.VISIBLE);
                    //on shutter callBack sound can be added ;
//                acceptCancelSelfie.setVisibility(View.VISIBLE);
                    toggleCaptureImageUi(View.GONE);
                    if (!imageCaptured) {
                        camera.takePicture(myShutterCallback, myPictureCallback_raw, myPictureCallback_JPG);
                        imageCaptured = true;
                    }
                }
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        final ImageView acceptSelfie = (ImageView) viewControl.findViewById(R.id.accept_selfie);
        acceptSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (highlightImage == 1) {
                    progressBar.setVisibility(View.VISIBLE);
                    saveEditPhotoTask = new SaveEditPhotoTask();
                    saveEditPhotoTask.execute();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        flashLightImageView = (ImageView) viewControl.findViewById(R.id.flash_light);
        boolean hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (hasFlash) {
            flashLightImageView.setVisibility(View.VISIBLE);
            flashLightImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Camera.Parameters params = camera.getParameters();
                    if (isFlashOn) {
                        camera.stopPreview();
                        isFlashOn = false;
                        flashLightImageView.setImageResource(R.drawable.ic_flash_off);
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(params);
                        camera.startPreview();
                    } else {
                        camera.stopPreview();
                        isFlashOn = true;
                        flashLightImageView.setImageResource(R.drawable.ic_flash_on);
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        try {
                            camera.setParameters(params);
                            camera.startPreview();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        switchCameraImage = (ImageView) viewControl.findViewById(R.id.flip_camera);
        if (hasFrontFacingCamera()) {
            switchCameraImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchCamera();
                }
            });
        } else {
            switchCameraImage.setVisibility(View.GONE);
        }
        final ImageView reTakePic = (ImageView) viewControl.findViewById(R.id.retake_selfie);
        reTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawView != null)
                    drawView.destroyDrawingCache();
                if (paintView != null)
                    paintView.removeAllViewsInLayout();
                imageCaptured = false;
                acceptCancelSelfie.setVisibility(View.GONE);
                paintView.setVisibility(View.GONE);
                if (hasFrontFacingCamera()) {
                    switchCameraImage.setVisibility(View.VISIBLE);
                }
                takePicView.setVisibility(View.VISIBLE);
                setVisibilityOfFlashLightImage();
                flashLightImageView.setImageResource(R.drawable.ic_flash_off);
                isFlashOn = false;
                initializeCamera(surfaceHolder);
                setupPreview();
            }
        });

    }

    private void toggleCaptureImageUi(int visibility) {
        if (hasFrontFacingCamera()) {
            switchCameraImage.setVisibility(visibility);
        }
        takePicView.setVisibility(visibility);
        flashLightImageView.setVisibility(visibility);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null || imageCaptured) {
            // preview surface does not exist
            return;
        }
        if (previewing) {
            camera.stopPreview();
            previewing = false;
        }

        if (camera == null) {
            initializeCamera(holder);
        }
        setupPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!imageCaptured) {
            initializeCamera(holder);
        }
        cameraControls.setVisibility(View.VISIBLE);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        previewing = false;
        cameraControls.setVisibility(View.GONE);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    private void switchCamera() {
        if (previewing) {
            camera.stopPreview();
            previewing = false;
        }
        camera.release();

        if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            currentCameraId = CAMERA_FACING_FRONT;
        } else {
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        initializeCamera(surfaceHolder);
        setupPreview();
    }

    private void setupPreview() {
        if (!this.isFinishing()) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                previewing = true;
            } catch (Exception e) {
                e.printStackTrace();
                if (!BuildConfig.DEBUG) {
                    Crashlytics.logException(e);
                }
                Toast.makeText(this, getString(R.string.camera_error_message), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private boolean checkFrontCameraExists() {
        int numCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (CAMERA_FACING_FRONT == info.facing) {
                return true;
            }
        }
        return false;
    }

    private void initializeCamera(SurfaceHolder holder) {

        try {
            if (hasFrontFacingCamera()) {
                camera = Camera.open(currentCameraId);
            } else {
                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
            }
        } catch (RuntimeException e) {
            Toast.makeText(this, getString(R.string.camera_error_message), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        setVisibilityOfFlashLightImage();
        setCameraDisplayOrientation(this, currentCameraId, camera);
        Camera.Parameters parameters = camera.getParameters();
        try {
            setPictureSize(parameters);
            setPreviewSize(parameters);
            camera.setParameters(parameters);
        } catch (Exception e) {
            if (!BuildConfig.DEBUG) {
                Crashlytics.logException(e);
            }
        }
    }

    private void setPictureSize(Camera.Parameters parameters) {
        boolean pictureSizeFound = false;
        for (Camera.Size size : parameters.getSupportedPictureSizes()) {
            if (((double) size.width * size.height) > ((double) 480 * 640) && ((size.width * 4 == size.height * 3) || (size.width * 3 == size.height * 4))) {
                parameters.setPictureSize(size.width, size.height);
                pictureSizeFound = true;
            }
        }
        if (!pictureSizeFound) {
            Crashlytics.log("Picture Size of 3:4 Not Found");
        }
    }

    private void setPreviewSize(Camera.Parameters parameters) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Camera.Size size1 = getBestPreviewSize(width, height, parameters);
        parameters.setPreviewSize(size1.width, size1.height);
        //below code set auto focus on camera
        if (parameters.getSupportedFocusModes().contains(
                Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
    }

    private void setVisibilityOfFlashLightImage() {
        if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            flashLightImageView.setVisibility(View.VISIBLE);
            if (isFlashOn) {
                isFlashOn = false;
                flashLightImageView.setImageResource(R.drawable.ic_flash_off);
            }
        } else {
            flashLightImageView.setVisibility(View.GONE);
        }
    }

    private Camera.Size getBestPreviewSize(int width, int height,
                                           Camera.Parameters parameters) {
        Camera.Size result = null;
        double area = 0.0;
        double trgetArea = 600 * 800;
        int screenWidth = width;
        int screenHeight = height;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            double widthC = (double) width / (double) size.height;
            double heightC = (double) height / (double) size.width;
            if (widthC < heightC) {
                int screenHeightTemp = (width * size.width) / size.height;
                double newArea = width * screenHeightTemp;

                if (newArea > area) {
                    area = newArea;
                    result = size;
                    screenHeight = screenHeightTemp;
                    screenWidth = width;
                }
            } else {
                int screenWidthTemp = (height * size.height) / size.width;

                double newArea = screenWidthTemp * height;
                if (Math.abs(newArea - trgetArea) < Math.abs(area - trgetArea)) {
                    area = newArea;
                    result = size;
                    screenHeight = height;
                    screenWidth = screenWidthTemp;
                }
            }
        }
        surfaceView.getLayoutParams().height = screenHeight;
        surfaceView.getLayoutParams().width = screenWidth;
        surfaceView.invalidate();
        return (result);
    }

    private boolean hasFrontFacingCamera() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mOrientationEventListener == null) {
            mOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {

                @Override
                public void onOrientationChanged(int orientation) {

                    // determine our orientation based on sensor response
                    int lastOrientation = mOrientation;

                    Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                    //if (display.getOrientation() == Surface.ROTATION_0) {   // landscape oriented devices
                    if (orientation >= 315 || orientation < 45) {
                        if (mOrientation != ORIENTATION_LANDSCAPE_NORMAL) {
                            mOrientation = ORIENTATION_LANDSCAPE_NORMAL;
                        }
                    } else if (orientation < 315 && orientation >= 225) {
                        if (mOrientation != ORIENTATION_PORTRAIT_INVERTED) {
                            mOrientation = ORIENTATION_PORTRAIT_INVERTED;
                        }
                    } else if (orientation < 225 && orientation >= 135) {
                        if (mOrientation != ORIENTATION_LANDSCAPE_INVERTED) {
                            mOrientation = ORIENTATION_LANDSCAPE_INVERTED;
                        }
                    } else if (orientation < 135 && orientation > 45) {
                        if (mOrientation != ORIENTATION_PORTRAIT_NORMAL) {
                            mOrientation = ORIENTATION_PORTRAIT_NORMAL;
                        }
                    }
                    /*} else {  // portrait oriented devices
                        if (orientation >= 315 || orientation < 45) {
                            if (mOrientation != ORIENTATION_PORTRAIT_NORMAL) {
                                mOrientation = ORIENTATION_PORTRAIT_NORMAL;
                            }
                        } else if (orientation < 315 && orientation >= 225) {
                            if (mOrientation != ORIENTATION_LANDSCAPE_NORMAL) {
                                mOrientation = ORIENTATION_LANDSCAPE_NORMAL;
                            }
                        } else if (orientation < 225 && orientation >= 135) {
                            if (mOrientation != ORIENTATION_PORTRAIT_INVERTED) {
                                mOrientation = ORIENTATION_PORTRAIT_INVERTED;
                            }
                        } else if (orientation <135 && orientation > 45) {
                            if (mOrientation != ORIENTATION_LANDSCAPE_INVERTED) {
                                mOrientation = ORIENTATION_LANDSCAPE_INVERTED;
                            }
                        }
                    }*/


                }
            };
        }
        if (mOrientationEventListener.canDetectOrientation()) {
            mOrientationEventListener.enable();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mOrientationEventListener.disable();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /*protected int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else {
            return data.getByteCount();
        }
    }*/

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void generateViews() {

        paintView.setVisibility(View.VISIBLE);
        // drawView=new MyView(this);
        drawView = new DrawingPanel(this, lastColor);
        drawView.setDrawingCacheEnabled(true);
//        drawView.measure(
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        drawView.layout(0, 0, drawView.getMeasuredWidth(),
//                drawView.getMeasuredHeight());
        drawView.buildDrawingCache(true);
        drawView.setLayoutParams(new LinearLayout.LayoutParams(
                canvasWidth, canvasHeight));
        paintView.addView(drawView);
    }

    //JIRA CH-532
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int action = event.getAction();
            int keyCode = event.getKeyCode();
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_UP:
                    if (action == KeyEvent.ACTION_UP) {
//                    Toast.makeText(this,"key up",Toast.LENGTH_SHORT).show();
                        if (!imageCaptured) {
                            camera.takePicture(myShutterCallback, myPictureCallback_raw, myPictureCallback_JPG);
                            imageCaptured = true;
                        }
                    }
                    return true;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    if (action == KeyEvent.ACTION_DOWN) {
//                    Toast.makeText(this,"key down",Toast.LENGTH_SHORT).show();
                        if (!imageCaptured) {
                            camera.takePicture(myShutterCallback, myPictureCallback_raw, myPictureCallback_JPG);
                            imageCaptured = true;
                        }
                    }
                    return true;
                default:
                    return super.dispatchKeyEvent(event);
            }
        }
        return false;
    }

    class SavePhotoTask extends AsyncTask<Byte, String, Boolean> {

        private final byte[] arg0;

        private SavePhotoTask(byte[] arg0) {
            this.arg0 = arg0;
        }

        @Override
        protected Boolean doInBackground(Byte[] bitmapImage) {
            Process.setThreadPriority(Process.THREAD_PRIORITY_MORE_FAVORABLE);
            File myPath = new File(imagePath.getPath());
            if (bitmapPicture != null) {
                bitmapPicture.recycle();
            }
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(arg0, 0, arg0.length, options);
//            options.inSampleSize = calculateInSampleSize(options, canvasWidth, canvasHeight);
            options.inSampleSize = calculateInSampleSize(options, 600, 800);
//            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
//            bitmapPicture = BitmapFactory.decodeByteArray(arg0, 0, arg0.length, options);
            Bitmap immutableBmp = BitmapFactory.decodeByteArray(arg0, 0, arg0.length, options);
            bitmapPicture = immutableBmp.copy(Bitmap.Config.ARGB_8888, true);
            if (bitmapPicture == null) {
                return false;
            }
//            int size = sizeOf(bitmapPicture);
//            if (size > 2 * 1024 * 1024) {
//                Matrix m = new Matrix();
//                m.setRectToRect(new RectF(0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight()), new RectF(0, 0, surfaceView.getWidth(), surfaceView.getHeight()), Matrix.ScaleToFit.CENTER);
//                bitmapPicture = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), m, true);
//            }
            Display display = getWindowManager().getDefaultDisplay();
            Point size1 = new Point();
            display.getSize(size1);
            canvasWidth = size1.x;

            Matrix mat = new Matrix();

            if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                switch (orientation) {
                    case 1:
                        mat.postRotate(90);
                        canvasHeight = (canvasWidth * 3) / 4;
                        break;
                    case 2:
                        mat.postRotate(-90);
                        canvasHeight = (canvasWidth * 3) / 4;
                        break;
                    case 3:
                        mat.postRotate(0);
                        canvasHeight = (canvasWidth * 4) / 3;
                        break;
                    case 4:
                        mat.postRotate(180);
                        canvasHeight = (canvasWidth * 4) / 3;
                        break;
                }
                mat.postRotate(90);
            } else {
                switch (orientation) {
                    case 1:
                        mat.postRotate(-90);
                        canvasHeight = (canvasWidth * 3) / 4;
                        break;
                    case 2:
                        mat.postRotate(90);
                        canvasHeight = (canvasWidth * 3) / 4;
                        break;
                    case 3:
                        mat.postRotate(0);
                        canvasHeight = (canvasWidth * 4) / 3;
                        break;
                    case 4:
                        mat.postRotate(180);
                        canvasHeight = (canvasWidth * 4) / 3;
                        break;
                }
                mat.postRotate(-90);
                bitmapPicture = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), mat, false);
                mat = new Matrix();
                mat.preScale(-1.0f, 1.0f);
            }

            bitmapPicture = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), mat, false);

            if (highlightImage == 0) {
                try {
                    FileOutputStream fos = new FileOutputStream(myPath);
                    if (!bitmapPicture.compress(Bitmap.CompressFormat.JPEG, 100, fos)) {
                        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    }
                    if (bitmapPicture != null)
                        bitmapPicture.recycle();
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!BuildConfig.DEBUG) {
                        Crashlytics.logException(e);
                    }
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            System.gc();
            progressBar.setVisibility(View.GONE);
            if (result) {
                acceptCancelSelfie.setVisibility(View.VISIBLE);
                if (highlightImage == 1)
                    generateViews();
            } else {
                toggleCaptureImageUi(View.VISIBLE);
                Toast.makeText(TakePhotoActivity.this, R.string.error_image_processing, Toast.LENGTH_LONG).show();
                if (camera != null) {
                    initializeCamera(surfaceHolder);
                    setupPreview();
                }
            }
            super.onPostExecute(result);
        }
    }

    class SaveEditPhotoTask extends AsyncTask<Void, Void, Boolean> {
        private SaveEditPhotoTask() {
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Process.setThreadPriority(Process.THREAD_PRIORITY_MORE_FAVORABLE);

            stretchTransparentBitmap();
//            Matrix m = new Matrix();
//            m.setRectToRect(new RectF(0, 0, transparentBitmap.getWidth(), transparentBitmap.getHeight()), new RectF(0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight()), Matrix.ScaleToFit.FILL);
//            transparentBitmap =  Bitmap.createBitmap(transparentBitmap, 0, 0, transparentBitmap.getWidth(), transparentBitmap.getHeight(), m, true);
//
            creatingEditedImage();
//            if (!bitmapPicture.isMutable())
//                bitmapPicture = bitmapPicture.copy(Bitmap.Config.ARGB_8888, true);
//            Canvas editedCanvas = new Canvas(bitmapPicture);
//            editedCanvas.drawBitmap(transparentBitmap, 0, 0, null);
            if (bitmapPicture != null) {
                try {
                    File myPath = new File(imagePath.getPath());
                    FileOutputStream fos = new FileOutputStream(myPath);
                    if (!bitmapPicture.compress(Bitmap.CompressFormat.JPEG, 100, fos)) {
                        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    }
                    fos.close();
                    if (bitmapPicture != null)
                        bitmapPicture.recycle();
                    if (transparentBitmap != null)
                        transparentBitmap.recycle();
                    transparentCanvas = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!BuildConfig.DEBUG) {
                        Crashlytics.logException(e);
                    }
                    return false;
                }
            }
            return true;
        }

        private void creatingEditedImage() {
            Canvas editedCanvas = new Canvas(bitmapPicture);
            editedCanvas.drawBitmap(transparentBitmap, 0, 0, null);
        }

        private void stretchTransparentBitmap() {
            Matrix m = new Matrix();
            m.setRectToRect(new RectF(0, 0, transparentBitmap.getWidth(), transparentBitmap.getHeight()), new RectF(0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight()), Matrix.ScaleToFit.FILL);
            transparentBitmap = Bitmap.createBitmap(transparentBitmap, 0, 0, transparentBitmap.getWidth(), transparentBitmap.getHeight(), m, true);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            System.gc();
            progressBar.setVisibility(View.GONE);
            if (result) {
                setResult(RESULT_OK);
                finish();
            } else {
                toggleCaptureImageUi(View.VISIBLE);
                Toast.makeText(TakePhotoActivity.this, R.string.error_image_processing, Toast.LENGTH_LONG).show();
                if (camera != null) {
                    initializeCamera(surfaceHolder);
                    setupPreview();
                }
            }
            super.onPostExecute(result);
        }
    }

    public class DrawingPanel extends View implements View.OnTouchListener {

        private static final float TOUCH_TOLERANCE = 0;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mPaint, mBitmapPaint;
        private ArrayList<PathPoints> paths = new ArrayList<>();
        private ArrayList<PathPoints> undonePaths = new ArrayList<>();
        private Bitmap mBitmap;
        private int color;
        private int x, y;
        private float mX, mY;

        public DrawingPanel(Context context, int color) {
            super(context);
            this.color = color;
            setFocusable(true);
            setFocusableInTouchMode(true);

            this.setOnTouchListener(this);
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(color);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(1);
            mPaint.setTextSize(30);

            mPath = new Path();
            paths.add(new PathPoints(mPath, color, false));
            mCanvas = new Canvas();

            if (transparentBitmap != null) {
//                transparentBitmap.recycle();
                transparentBitmap.eraseColor(Color.TRANSPARENT);
                transparentCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
//                Bitmap bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
//                transparentBitmap = Bitmap.createBitmap(bitmap);
//                transparentCanvas = new Canvas(transparentBitmap);
            } else {
                transparentBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
                transparentCanvas = new Canvas(transparentBitmap);
                transparentCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            }
        }

        public void colorChanged(int color) {
            this.color = color;
            mPaint.setColor(color);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createScaledBitmap(bitmapPicture, canvasWidth,
                    canvasHeight, true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            transparentCanvas.drawBitmap(transparentBitmap, 0, 0, mBitmapPaint);
            for (PathPoints p : paths) {
                mPaint.setColor(p.getColor());
                Log.v("", "Color code : " + p.getColor());
                if (p.isTextToDraw()) {
                    canvas.drawText(p.textToDraw, p.x, p.y, mPaint);
                } else {
                    canvas.drawPath(p.getPath(), mPaint);
//                    transparentCanvas.drawPath(p.getPath(), mPaint);
                }
            }
        }

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            transparentCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath = new Path();
            paths.add(new PathPoints(mPath, color, false));
            Snackbar snackbar = Snackbar
                    .make(drawView, getString(R.string.image_highlighted), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickUndo();
                            generateViews();
                            Snackbar snackbar1 = Snackbar.make(drawView, getString(R.string.image_is_restored), Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();

        }

        private void drawText(int x, int y) {
            Log.v(TAG, "Here");
            Log.v(TAG, "X " + x + " Y " + y);
            this.x = x;
            this.y = y;
            paths.add(new PathPoints(color, textToDraw, true, x, y));
            // mCanvas.drawText(textToDraw, x, y, mPaint);
        }

        @Override
        public boolean onTouch(View arg0, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!isTextModeOn) {
                        touch_start(x, y);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!isTextModeOn) {
                        touch_move(x, y);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isTextModeOn) {
                        drawText((int) x, (int) y);
                        invalidate();
                    } else {
                        touch_up();
                        invalidate();
                    }
                    break;
            }
            return true;
        }

        public void onClickUndo() {
            if (paths.size() > 0) {
                undonePaths.add(paths.remove(paths.size() - 1));
                invalidate();
            } else {

            }
            // toast the user
        }

        public void onClickRedo() {
            if (undonePaths.size() > 0) {
                paths.add(undonePaths.remove(undonePaths.size() - 1));
                invalidate();
            } else {

            }
            // toast the user
        }
    }

    class PathPoints {
        private Path path;
        // private Paint mPaint;
        private int color;
        private String textToDraw;
        private boolean isTextToDraw;
        private int x, y;

        public PathPoints(Path path, int color, boolean isTextToDraw) {
            this.path = path;
            this.color = color;
            this.isTextToDraw = isTextToDraw;
        }

        public PathPoints(int color, String textToDraw, boolean isTextToDraw,
                          int x, int y) {
            this.color = color;
            this.textToDraw = textToDraw;
            this.isTextToDraw = isTextToDraw;
            this.x = x;
            this.y = y;
        }

        public Path getPath() {
            return path;
        }

        public void setPath(Path path) {
            this.path = path;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getTextToDraw() {
            return textToDraw;
        }

        public boolean isTextToDraw() {
            return isTextToDraw;
        }

        public void setTextToDraw(String textToDraw) {
            this.textToDraw = textToDraw;
        }

        public void setTextToDraw(boolean isTextToDraw) {
            this.isTextToDraw = isTextToDraw;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

    }

}