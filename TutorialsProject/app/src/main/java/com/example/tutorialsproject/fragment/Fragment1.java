package com.example.tutorialsproject.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.viewmodel.PracticeViewModel;

public class Fragment1 extends Fragment {
    private String TAG = "FRAGMENTEXAMPLE";
    public View view;
    public PracticeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate Fragement1");
        viewModel = ViewModelProviders.of(getActivity()).get(PracticeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        Log.d(TAG,"onCreateView Fragement1");
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG,"onAttach Fragement1");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated Fragement1");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart Fragement1");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume Fragement1");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause Fragement1");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop Fragement1");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView Fragement1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy Fragement1");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach Fragement1");
    }
}
