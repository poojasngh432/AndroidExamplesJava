package com.assignment.pristyncareproject.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.pristyncareproject.R;
import com.assignment.pristyncareproject.data.model.Photo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    Context context;
    List<String> photoList;
    private OnItemClickListener onItemClickListener;

    public MainAdapter(Context context, List<String> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_single_item, parent, false);
        // lets create a recyclerview row item layout file
        return new MainViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {

        String url = photoList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.white_bg);
        requestOptions.error(R.drawable.ic_red_rejected);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
       // requestOptions.circleCrop();

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imageView;
        OnItemClickListener onItemClickListener;
        ProgressBar progressBar;

        public MainViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.prod_image);
            progressBar = itemView.findViewById(R.id.progress_load_photo);

            imageView.setOnClickListener(this);
            this.onItemClickListener = onItemClickListener;
        }


        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }

    }

}
