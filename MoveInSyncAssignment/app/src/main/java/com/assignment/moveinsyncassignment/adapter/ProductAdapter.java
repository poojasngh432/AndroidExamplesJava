package com.assignment.moveinsyncassignment.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.assignment.moveinsyncassignment.R;
import com.assignment.moveinsyncassignment.activity.Productdetails;
import com.assignment.moveinsyncassignment.database.model.Product;
import com.assignment.moveinsyncassignment.database.model.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productsList;
    private OnItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.products_row_item, parent, false);
        return new ProductViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.prodName.setText(productsList.get(position).getName());
        holder.prodDesc.setText(productsList.get(position).getDescription());
        holder.prodPrice.setText("₹ "+String.valueOf(productsList.get(position).getPrice()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.white_bg);
        requestOptions.error(R.drawable.ic_heart);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.circleCrop();

        Glide.with(context)
                .load(productsList.get(position).getUrlString())
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
                .into(holder.prodImage);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView prodImage;
        TextView prodName, prodDesc, prodPrice;
        OnItemClickListener onItemClickListener;
        ProgressBar progressBar;

        public ProductViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            prodImage = itemView.findViewById(R.id.prod_image);
            prodName = itemView.findViewById(R.id.prod_name);
            prodPrice = itemView.findViewById(R.id.prod_price);
            prodDesc = itemView.findViewById(R.id.prod_desc);
            progressBar = itemView.findViewById(R.id.progress_load_photo);

            this.onItemClickListener = onItemClickListener;

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

}
