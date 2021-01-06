package com.example.tutorialsproject.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialsproject.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] listdata;

    // RecyclerView recyclerView;
    public CustomAdapter(String[] listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //is called v number of times, v = no. of views visible on screen in rv
        //max calls = n
        //doesn't get called while repititive scrolling

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.single_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        Log.d("RECYCLERVIEWTEST","onCreateViewHolder, viewType: " + viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //is called v number of times, v = no. of views visible on screen in rv
        //max calls = n
        //doesn't get called while repititive scrolling

        final String myListData = listdata[position];
        holder.textView.setText(listdata[position]);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: " + myListData,Toast.LENGTH_LONG).show();
            }
        });
        Log.d("RECYCLERVIEWTEST","onBindViewHolder, position: " + position);
    }


    @Override
    public int getItemCount() {
        //is called n no. of times, n = number of items in dataset
        //then VieHolder class is called
        //will keep getting called as we scroll
        //max calls = n

        Log.d("RECYCLERVIEWTEST","getItemCount, position: " + listdata.length);
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //is called v number of times, v = no. of views visible on screen in rv
        //max calls = n

        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.d("RECYCLERVIEWTEST","ViewHolder class");
            this.textView = (TextView) itemView.findViewById(R.id.textview_rv);
        }
    }
}