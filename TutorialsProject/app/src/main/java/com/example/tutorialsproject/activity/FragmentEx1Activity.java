package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.fragment.FirstFragment;
import com.example.tutorialsproject.fragment.Fragment1;

public class FragmentEx1Activity extends AppCompatActivity {
    private static final String TAG = "FRAGMENTEXAMPLE";
    private String name = "CAPTAIN AMERICA";

    //Retaining data in Fragment example

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_ex1);

        Log.d(TAG,"onCreate FragmentActivity" + name);

        // restore saved state
        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            Log.d(TAG,"onCreate inside " + name);
        }else {
            //To dynamically add a fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FirstFragment fragment = new FirstFragment();
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack("fragment");
            fragmentTransaction.commit();

            //Different transaction instants are important so that we can revert them later
            //can't use commit() twice on same instance of transaction instance
            //if all the transactions are performed on same transaction instance, then you only commit() them once in the end. And when you press back button all transactions will be reverted at the same time
            FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction2.add(R.id.fragment_container, fragment1);
            fragmentTransaction2.addToBackStack("fragment");
            fragmentTransaction2.commit();

//            // replace
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            ft.replace(R.id.fragment_container, new Fragment1());
//            ft.commit();
//
//           // remove
//            Fragment fragment1 = fragmentManager.findFragmentById(R.id.fragment_container);
//            FragmentTransaction ft2 = fragmentManager.beginTransaction();
//            ft2.remove(fragment);
//            ft2.commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save current instance state
        name = "TONY STARK";
        outState.putString("name",name);
        super.onSaveInstanceState(outState);

        Log.d(TAG,"onSaveInstanceState FragmentActivity" + name);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // restore saved state
        super.onRestoreInstanceState(savedInstanceState);
        name = savedInstanceState.getString("name");

        if (savedInstanceState != null) {
            Log.d(TAG,"onRestoreInstanceState FragmentActivity" + name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart FragmentActivity" + name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume FragmentActivity" + name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause method FragmentActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop method FragmentActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart method FragmentActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy method FragmentActivity");
    }
}
