package com.example.tutorialsproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tutorialsproject.R;

/**
 * Sample fragment to demonstrate the instantiation of fragments with arguments
 *
 * Created by Günhan on 28.10.2015.
 */

public class MyFragment extends Fragment {
    private String TAG = "MYFRAGMENTEXAMPLE";

    private String name;
    private int age;

    private TextView mNameTextView;
    private TextView mAgeTextView;

    public static MyFragment newInstance(String name, int age) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putInt("age", age);

        //To instantiate the frafment, we'll need to call
        //Fragment fragment = MyFragment.newInstance("Gunhan", 28);

        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            name = bundle.getString("name");
            age = bundle.getInt("age");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG,"onAttach FirstFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate FirstFragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated FirstFragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart FirstFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume FirstFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause FirstFragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop FirstFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView FirstFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy FirstFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach FirstFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        mNameTextView = (TextView) view.findViewById(R.id.textview_rv);
        mAgeTextView = (TextView) view.findViewById(R.id.button_first);

        readBundle(getArguments());

        mNameTextView.setText(String.format("Name: %s", name));
        mAgeTextView.setText(String.format("Age: %d", age));

        Log.d(TAG,"onDetach MyFragment");

        return view;
    }
}
