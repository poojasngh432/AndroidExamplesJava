package com.example.picassoexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    int i = 0;
    Button btnDrawableImage, btnUrlImage, btnErrorImage, btnPlaceholderImage, btnCallback, btnResizeImage, btnRotateImage, btnScaleImage, btnTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        imageView = (ImageView) findViewById(R.id.imageView);
        btnDrawableImage = (Button) findViewById(R.id.btnDrawable);
        btnUrlImage = (Button) findViewById(R.id.btnUrl);
        btnPlaceholderImage = (Button) findViewById(R.id.btnPlaceholder);
        btnErrorImage = (Button) findViewById(R.id.btnError);
        btnCallback = (Button) findViewById(R.id.btnCallBack);
        btnResizeImage = (Button) findViewById(R.id.btnResize);
        btnRotateImage = (Button) findViewById(R.id.btnRotate);
        btnScaleImage = (Button) findViewById(R.id.btnScale);
        btnTarget = (Button) findViewById(R.id.btnTarget);

        btnDrawableImage.setOnClickListener(this);
        btnPlaceholderImage.setOnClickListener(this);
        btnUrlImage.setOnClickListener(this);
        btnCallback.setOnClickListener(this);
        btnResizeImage.setOnClickListener(this);
        btnErrorImage.setOnClickListener(this);
        btnRotateImage.setOnClickListener(this);
        btnScaleImage.setOnClickListener(this);
        btnTarget.setOnClickListener(this);

    }

    //1. Drawable : This button click invokes the most basic feature of Picasso i.e. Loading A drawable image into an ImageView
    //2. Placeholder : A placeholder is commonly used to display a drawable image while the main image gets loaded into the imageview. This is essential in cases when the image takes time to load from the web.
    //3. URL: To load an image from a URl, the url is enclosed as a String inside the load() method
    //4. Error : An error drawable is generally used in cases where there’s a failure in loading the image. In this case the interim placeholder image gets replaced by the error drawable that’s placed inside .error() method.
    //5. Callback : Picasso provides callback methods through which we can keep a check of the status of the image loaded (success/error) and display a text accordingly. We’ve displayed a Toast message for the same when an error occurs as show below.
    //6. Resize : Picasso allows us to use a resize the image before displaying it in an ImageView by invoking the method resize() and passing the desired width and height in pixels
    //7. Rotate : To rotate an image pass the float value inside the rotate() method. The image is rotated in degrees upon it’s anchor point (0,0)
    //8. Scale : Resizing an image can cause stretched images. To keep the aspect ratio intact use centerCrop() or centerInside() with the resize() method.
    //fit() is like a delayed resize(), that reduces the image to fit the ImageView bounds.
    //Note : fit cannot be used with resize() since it has a built-in resize. centerCrop and centerInside can’t be used without calling resize() with a positive width and height
    //9. Targets : This is another form of callback that’s used as a listener for image loading. Targets are an interface that would return the bitmap image along with its placeholder and error drawable(if defined). We can further customise the bitmap image returned in the method onBitmapLoaded() or directly show it in the ImageView

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDrawable:
                Picasso.with(this).load(R.drawable.image).into(imageView);
                break;
            case R.id.btnPlaceholder:
                Picasso.with(this).load("https://www.cheatsheet.com/wp-content/uploads/2019/08/Kermit.jpg").placeholder(R.drawable.placeholder).into(imageView);
                break;
            case R.id.btnUrl:
                Picasso.with(this).load("https://i.pinimg.com/736x/33/04/ff/3304ffca87839427ca71997e2d2342e9.jpg").placeholder(R.drawable.placeholder).into(imageView);
                break;
            case R.id.btnError:
                Picasso.with(this).load("www.journaldev.com").placeholder(R.drawable.placeholder).error(R.drawable.error).into(imageView);
                break;
            case R.id.btnCallBack:
                Picasso.with(this).load("www.journaldev.com").error(R.mipmap.ic_launcher).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("TAG", "onSuccess");
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btnResize:
                Picasso.with(this).load(R.drawable.image).resize(200, 200).into(imageView);
                break;
            case R.id.btnRotate:
                Picasso.with(this).load(R.drawable.image).rotate(90f).into(imageView);
                break;
            case R.id.btnScale:

                if (i == 3)
                    i = 0;

                else {
                    if (i == 0) {
                        Picasso.with(this).load(R.drawable.image).fit().into(imageView);
                        Toast.makeText(getApplicationContext(), "Fit", Toast.LENGTH_SHORT).show();
                    } else if (i == 1) {
                        Picasso.with(this).load(R.drawable.image).resize(200, 200).centerCrop().into(imageView);
                        Toast.makeText(getApplicationContext(), "Center Crop", Toast.LENGTH_SHORT).show();
                    } else if (i == 2) {
                        Picasso.with(this).load(R.drawable.image).resize(200, 200).centerInside().into(imageView);
                        Toast.makeText(getApplicationContext(), "Center Inside", Toast.LENGTH_SHORT).show();
                    }
                    i++;
                }
                break;

            case R.id.btnTarget:
                Picasso.with(this).load("https://d3fa68hw0m2vcc.cloudfront.net/3e6/218354982.jpeg").placeholder(R.drawable.placeholder).error(R.drawable.error).into(target);
                break;
        }
    }

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            imageView.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            imageView.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            imageView.setImageDrawable(placeHolderDrawable);
        }
    };
}
