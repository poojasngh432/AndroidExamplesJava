package com.example.tutorialsproject.util;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    public static MyFragment newInstance(String tag) {
        MyFragment f = new MyFragment();
        Bundle args = new Bundle();
        args.putString("tag", tag);
        f.setArguments(args);
        return f;
    }

    public static MyFragment newInstance(String tag, Bundle args) {
        MyFragment f = new MyFragment();
        args.putString("tag", tag);
        f.setArguments(args);
        return f;
    }
}
