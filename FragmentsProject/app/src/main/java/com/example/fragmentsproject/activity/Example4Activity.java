package com.example.fragmentsproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.fragmentsproject.R;
import com.example.fragmentsproject.fragment.AboutFragment;
import com.example.fragmentsproject.fragment.GlobeFragment;
import com.example.fragmentsproject.fragment.HomeFragment;
import com.example.fragmentsproject.fragment.MailFragment;
import com.example.fragmentsproject.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Example4Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example4);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_globe:
                            openFragment(GlobeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_about:
                            openFragment(AboutFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_mail:
                            openFragment(MailFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_profile:
                            openFragment(ProfileFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
}
