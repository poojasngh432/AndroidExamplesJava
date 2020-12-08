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
import com.example.tutorialsproject.util.RandomUtils;

public class OrientationActivity extends AppCompatActivity implements FragmentActionListener {
    private static final String COMMON_TAG = "OrientationChange";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();
    private int randomNo;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        fragmentManager = getSupportFragmentManager();
        addCountriesFragment();
        randomNo = RandomUtils.getRandom(100);

        Log.i(COMMON_TAG, "MainActivity onCreate : random number is - " + randomNo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(COMMON_TAG, "MainActivity onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(COMMON_TAG, "MainActivity onRestoreInstanceState");
    }

    private void addCountriesFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountriesFragment countryListFragment = new CountriesFragment();
        countryListFragment.setFragmentActionListener(OrientationActivity.this);

        fragmentTransaction.add(R.id.fragmentContainer, countryListFragment);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(String countryName) {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();

        Bundle bundle = new Bundle();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countryName);
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer, countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();
    }

    @Override
    public void onCountrySelected(String country) {
        addCountryDescriptionFragment(country);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(COMMON_TAG, "onConfigurationChanged " + randomNo);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(COMMON_TAG, "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i(COMMON_TAG, "portrait");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(COMMON_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(COMMON_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(COMMON_TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(COMMON_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(COMMON_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(COMMON_TAG, "onDestroy");
    }

}

