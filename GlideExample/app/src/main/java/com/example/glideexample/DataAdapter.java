package com.example.glideexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<ImageUrl> imageUrls;
    private Context context;
    private ArrayList namesOfImages;

    public DataAdapter(Context context, ArrayList<ImageUrl> imageUrls, ArrayList namesOfImages) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.namesOfImages = namesOfImages;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false);
        return new ViewHolder(view);
    }


     // gets the image url from adapter and passes to Glide API to load the image

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(context).load(imageUrls.get(i).getImageUrl()).fitCenter().into(viewHolder.img);
        viewHolder.nameOfImg.setText(namesOfImages.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView nameOfImg;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageView);
            nameOfImg = view.findViewById(R.id.name_of_img);
        }
    }
}
