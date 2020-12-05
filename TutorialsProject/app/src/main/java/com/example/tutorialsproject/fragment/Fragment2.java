package com.example.tutorialsproject.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.viewmodel.PracticeViewModel;

public class Fragment2 extends Fragment {
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

}
