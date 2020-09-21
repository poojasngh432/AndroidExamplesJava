package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.tutorialsproject.DataBindingInterface;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.Product;
import com.example.tutorialsproject.databinding.ActivityDataBindingBinding;
import com.example.tutorialsproject.fragment.ChooseQuantityDialog;
import com.example.tutorialsproject.util.Products;

public class DataBindingActivity extends AppCompatActivity implements DataBindingInterface{

    //data binding
    ActivityDataBindingBinding mBinding;

    //vars
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        Products products = new Products();
        mProduct = products.PRODUCTS[0];

        mBinding.setProduct(mProduct);
        mBinding.setQuantity(1);
        mBinding.setIDataBinding((DataBindingInterface) this);
        mBinding.setTestUrl("https://i.redd.it/g5acbfi5hkm01.jpg");
    }

    @Override
    public void inflateQuantityDialog() {
        ChooseQuantityDialog dialog = new ChooseQuantityDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.dialog_choose_quantity));
    }

    @Override
    public void setQuantity(int quantity) {
        mBinding.setQuantity(quantity);
    }
}
