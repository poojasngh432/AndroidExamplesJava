package com.example.browsefromstorage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.browsefromstorage.R;

import java.util.ArrayList;

public class SelectMultipleImagesActivity extends AppCompatActivity implements View.OnClickListener{
    private Button browseGalleryBtn;
    private int PICK_IMAGES = 2;

    private LinearLayout lnrImages;
    private Button btnAddPhots;
    private Button btnSaveImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;
    private final int PICK_IMAGE_MULTIPLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_multiple_images);
        browseGalleryBtn = findViewById(R.id.browse_imgs);

        lnrImages = (LinearLayout)findViewById(R.id.lnrImages);
        btnAddPhots = (Button)findViewById(R.id.btnAddPhots);
        btnSaveImages = (Button)findViewById(R.id.btnSaveImages);
        btnAddPhots.setOnClickListener(this);
        btnSaveImages.setOnClickListener(this);

        browseGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImageSelection();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddPhots:
                Intent intent = new Intent(SelectMultipleImagesActivity.this,CustomPhotoGalleryActivity.class);
                startActivityForResult(intent,PICK_IMAGE_MULTIPLE);
                break;
            case R.id.btnSaveImages:
                if(imagesPathList !=null){
                    if(imagesPathList.size()>1) {
                        Toast.makeText(SelectMultipleImagesActivity.this, imagesPathList.size() + " no of images are selected", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SelectMultipleImagesActivity.this, imagesPathList.size() + " no of image are selected", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SelectMultipleImagesActivity.this," no images are selected", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void startImageSelection(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        if(requestCode==PICK_IMAGES){
            if(resultCode==RESULT_OK){
                //data.getParcelableArrayExtra(name);
                //If Single image selected then it will fetch from Gallery
                if(data.getData()!=null){
                    Uri mImageUri=data.getData();
                }else{
                    if(data.getClipData()!=null){
                        ClipData mClipData=data.getClipData();
                        ArrayList<Uri> mArrayUri=new ArrayList<Uri>();
                        for(int i=0;i<mClipData.getItemCount();i++){
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                        }
                        Log.v("LOG_TAG", "Selected Images"+ mArrayUri.size());
                        Toast.makeText(this, "Selected Images: "+ mArrayUri.size(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }else {
                Toast.makeText(this, "You haven't picked any Image",
                        Toast.LENGTH_LONG).show();
            }
        }else  if(requestCode == PICK_IMAGE_MULTIPLE){
            imagesPathList = new ArrayList<String>();
            String[] imagesPath = data.getStringExtra("data").split("\\|");
            try{
                lnrImages.removeAllViews();
            }catch (Throwable e){
                e.printStackTrace();
            }
            for (int i=0;i<imagesPath.length;i++){
                imagesPathList.add(imagesPath[i]);
                yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(yourbitmap);
                imageView.setAdjustViewBounds(true);
                lnrImages.addView(imageView);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
