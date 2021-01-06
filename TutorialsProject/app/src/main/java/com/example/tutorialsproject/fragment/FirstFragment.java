package com.example.tutorialsproject.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.generated.callback.OnClickListener;

public class FirstFragment extends Fragment {
    private String TAG = "FRAGMENTEXAMPLE";

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
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container,false);

        /**
         * The final false argument to LayoutInflator.inflate() prevents the inflator from automatically attaching the inflated view hierarchy to the parent container. This is important, because the activity automatically attaches the view hierarchy to the parent as appropriate.
         */

        Button nextBtn = view.findViewById(R.id.button_first);
        Log.d(TAG,"onCreateView FirstFragment");

        return view;
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
