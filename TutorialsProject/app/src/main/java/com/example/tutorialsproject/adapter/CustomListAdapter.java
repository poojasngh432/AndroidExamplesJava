package com.example.tutorialsproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.ListItem;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<ListItem> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<ListItem> listData) {
        this.listData = listData;
        Log.d("LISTVIEWTEST","starting ListView..");
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        //can be called multiple times initially
        //called when dataset is changed

        Log.d("LISTVIEWTEST","getCount : " + listData.size());
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("LISTVIEWTEST","getItem : " + position + " and data is : " + listData.get(position));
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup vg) {
        //is called n number of times where n = no. of visible views on screen
        //is called again when scrolling, k no of times = no. of new items visible after scrolling
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(listData.get(position).getName());
        holder.uDesignation.setText(listData.get(position).getDesignation());
        holder.uLocation.setText(listData.get(position).getLocation());

        Log.d("LISTVIEWTEST","getView, position : " + position);

        return v;
    }

    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }

}
