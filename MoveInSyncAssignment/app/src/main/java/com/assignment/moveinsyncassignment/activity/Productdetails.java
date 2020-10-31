package com.assignment.moveinsyncassignment.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.assignment.moveinsyncassignment.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class Productdetails extends AppCompatActivity {
    String name, desc, price, url;
    TextView pName, pDesc, pPrice;
    ImageView pImage, back;
    Context context;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        context = Productdetails.this;

        name = getIntent().getStringExtra("name");
        desc = getIntent().getStringExtra("desc");
        price = getIntent().getStringExtra("price");
        url = getIntent().getStringExtra("url");

        pName = findViewById(R.id.product_name);
        pDesc = findViewById(R.id.product_desc);
        pPrice = findViewById(R.id.product_price);
        pImage = findViewById(R.id.product_image);
        progressBar = findViewById(R.id.progress_bar);
        back = findViewById(R.id.back);

        pName.setText(name);
        pDesc.setText(desc);
        pPrice.setText("₹ "+ price);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.white_bg);
        requestOptions.error(R.drawable.ic_red_rejected);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        //requestOptions.circleCrop();

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(pImage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
