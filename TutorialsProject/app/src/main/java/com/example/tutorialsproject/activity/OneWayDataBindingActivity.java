package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.Product;
import com.example.tutorialsproject.databinding.ActivityOneWayDataBindingBinding;
import com.example.tutorialsproject.util.Products;

import java.math.BigDecimal;

public class OneWayDataBindingActivity extends AppCompatActivity {
    ActivityOneWayDataBindingBinding activityOneWayDataBindingBinding;
    static Activity thisActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;

        activityOneWayDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_one_way_data_binding);

        Product p = Products.FRANCE_MOUNTAINS_PICTURE;

        activityOneWayDataBindingBinding.setProductVar(p);
    }

    @BindingAdapter({"isVisible"})
    public static void setIsVisible(ImageView imageView, BigDecimal rating){
        if(rating.intValue() < 5){
            imageView.setVisibility(View.GONE);
            Toast.makeText(thisActivity, "Rating is very low.", Toast.LENGTH_SHORT).show();
        }else{
            imageView.setVisibility(View.VISIBLE);
            Toast.makeText(thisActivity, "Rating is " + String.valueOf(rating.intValue()), Toast.LENGTH_SHORT).show();
        }

    }

}
