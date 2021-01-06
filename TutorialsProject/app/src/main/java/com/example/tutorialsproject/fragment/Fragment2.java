package com.example.tutorialsproject.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.viewmodel.PracticeViewModel;

public class Fragment2 extends Fragment {
    private String TAG = "FRAGMENT2EXAMPLE";
    public PracticeViewModel viewModel;
    public View view;
    public TextView nameTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(PracticeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        nameTV = view.findViewById(R.id.nameTV);

        viewModel.getName().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
              nameTV.setText(s);
            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG,"onAttach FirstFragment");
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

}
