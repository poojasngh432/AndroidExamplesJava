package com.example.tutorialsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.GithubUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MovieViewHolder> {
    private List<GithubUser> user;
    private int rowLayout;
    private Context context;

    public RepoAdapter(List<GithubUser> user, int rowLayout, Context context) {
        this.user = user;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout userLayout;
        TextView login;
        TextView htmlUrl;
        TextView rating;
        ImageView avatarImage;

        public MovieViewHolder(View v) {
            super(v);
            userLayout = (LinearLayout) v.findViewById(R.id.user_layout);
            avatarImage = (ImageView) v.findViewById(R.id.avatar_image);
            login = (TextView) v.findViewById(R.id.login);
            htmlUrl = (TextView) v.findViewById(R.id.html_url);
            rating = (TextView) v.findViewById(R.id.score);
        }
    }
    @Override
    public RepoAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String image_url = user.get(position).getAvatar_url();

        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.avatarImage);
        holder.login.setText(user.get(position).getLogin());
        holder.htmlUrl.setText(user.get(position).getHtml_url());
        holder.rating.setText(String.valueOf(user.get(position).getScore()));
    }
    @Override
    public int getItemCount() {
        return user.size();
    }
}