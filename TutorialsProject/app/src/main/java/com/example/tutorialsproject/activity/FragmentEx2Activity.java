package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.fragment.FirstFragment;
import com.example.tutorialsproject.fragment.Fragment1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentEx2Activity extends AppCompatActivity {
    private static final String TAG = "FRAGMENTEXAMPLE";
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.replace_btn)
    Button replaceBtn;

    //add, replace functioning example

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_ex2);
        Log.d(TAG,"onCreate FragmentEx2Activity");
        ButterKnife.bind(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FirstFragment fragment = new FirstFragment();
                fragmentTransaction.add(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack("fragment");
                fragmentTransaction.commit();
            }
        });

        replaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.fragment_container, new Fragment1());
                ft.addToBackStack("fragment");
                ft.commit();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart FragmentEx2Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume FragmentEx2Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause method FragmentEx2Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop method FragmentEx2Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart method FragmentEx2Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy method FragmentEx2Activity");
    }

}
