package com.example.savetostorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveToInternalStorageActivity extends AppCompatActivity {
    private ImageView imgOne;
    private Button btn;
    private OutputStream outputStream;
    Context context = SaveToInternalStorageActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgOne = findViewById(R.id.img_one_iv);
        btn = findViewById(R.id.saveBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgOne.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                File filepath = context.getExternalFilesDir("");
                File dir = new File(filepath.getAbsolutePath() + "/Demo/");

                //image is saved in Android->data->app folder->files->Demo->image will be here

                dir.mkdir();
                File file = new File(dir, System.currentTimeMillis() + ".jpg");
                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG,100, outputStream);
                Toast.makeText(getApplicationContext(),"Image saved to internal storage in Demo Folder", Toast.LENGTH_LONG).show();
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
