package com.example.fragmentexample3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class ListMenuFragment extends ListFragment {
    String[] countries = new String[] { "India", "Greece","Germany","Italy","Canada","Switzerland","Portugal", "Netherlands", "New Zealand", "Norway", "Denmark", "Malaysia" };
    String[] capital = new String[]{"Delhi", "Athens","Berlin","Rome","Ottawa","Bern","Lisbon", "The Hague", "Wellington", "Oslo", "Copenhagen", "Kuala Lumpur"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.listitems_info, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, countries);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailsFragment txt = (DetailsFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change("Country: "+ countries[position],"Capital : "+ capital[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}
