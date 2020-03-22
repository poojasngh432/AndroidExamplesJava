package com.example.fragmentexample3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class DetailsFragment extends Fragment {

    TextView country, capital;
    LinearLayout backgroundView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_info, container, false);
        country = (TextView)view.findViewById(R.id.Name);
        capital = (TextView)view.findViewById(R.id.Location);
        backgroundView = (LinearLayout) view.findViewById(R.id.background_view);
        return view;
    }
    public void change(String uname, String ulocation){
        country.setText(uname);
        capital.setText(ulocation);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        view.setBackgroundColor(color);
    }
}
