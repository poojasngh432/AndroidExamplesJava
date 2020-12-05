package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.Product;
import com.example.tutorialsproject.databinding.ActivityTwoWayDataBindingBinding;
import com.example.tutorialsproject.util.Products;

import java.math.BigDecimal;

public class TwoWayDataBindingActivity extends AppCompatActivity {
    static ActivityTwoWayDataBindingBinding activityTwoWayDataBindingBinding;
    static Activity thisActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;

        activityTwoWayDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_data_binding);

        Product p = Products.FRANCE_MOUNTAINS_PICTURE;

        activityTwoWayDataBindingBinding.setProductVar2(p);
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

    @BindingAdapter({"onTextChange"})
    public static void setOnTextChange(EditText et, String string){
        Toast.makeText(thisActivity, string, Toast.LENGTH_SHORT).show();
    }

    @BindingAdapter({"textreset"})
    public static void setTextreset(TextView textView, String title){
        activityTwoWayDataBindingBinding.tvProduct.setText(title);
        textView.setText(title);
    }
}
