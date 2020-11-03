package com.example.fragmentsproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentsproject.R;

public class Fragment2 extends Fragment {
    TextView textView;
    String str;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        textView = view.findViewById(R.id.textview);

        return view;
    }

    public void change(){
        str = textView.getText().toString();
        int val = Integer.valueOf(str);
        val++;
        textView.setText(String.valueOf(val));
    }

}
