package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.tutorialsproject.Interface.FragmentActionListener;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.fragment.CountriesFragment;
import com.example.tutorialsproject.fragment.CountryDescriptionFragment;

public class OrientationActivity extends AppCompatActivity implements FragmentActionListener{
    private static final String COMMON_TAG = "OrientationChange";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        fragmentManager = getSupportFragmentManager();
        addCountriesFragment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(COMMON_TAG,"MainActivity onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(COMMON_TAG,"MainActivity onRestoreInstanceState");
    }

    private void addCountriesFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();

        CountriesFragment countryListFragment = new CountriesFragment();
        countryListFragment.setFragmentActionListener(OrientationActivity.this);

        fragmentTransaction.add(R.id.fragmentContainer,countryListFragment);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(String countryName){
        fragmentTransaction = fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();

        Bundle bundle = new Bundle();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY,countryName);
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCountrySelected(String country) {
        addCountryDescriptionFragment(country);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(COMMON_TAG, "landscape");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(COMMON_TAG, "portrait");
        }
    }
}
