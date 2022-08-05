package com.example.githubuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubuser.R;
import com.example.githubuser.model.UserGithub;

import java.util.ArrayList;

public class ListUserGithubAdapter extends RecyclerView.Adapter<ListUserGithubAdapter.ListViewHolder> {
    private onItemClickCallback onItemClickCallback;
    private ArrayList<UserGithub> mData = new ArrayList<>();

    public void setData(ArrayList<UserGithub> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(onItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_github, parent, false);
        return new ListViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.bind(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvtr;
        TextView tvUser, tvName, tvLoca;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvtr = itemView.findViewById(R.id.img_avtr);
//            tvName = itemView.findViewById(R.id.tv_name);
            tvUser = itemView.findViewById(R.id.tv_user);
//            tvLoca = itemView.findViewById(R.id.tv_loca);
        }

        public void bind(UserGithub userGithub) {
            Glide.with(itemView.getContext())
                    .load(userGithub.getAvtr())
                    .apply(new RequestOptions().override(55,55))
                    .into(imgAvtr);
//            tvName.setText(userGithub.getName());
            tvUser.setText(userGithub.getUser());
//            tvLoca.setText(userGithub.getLoca());
        }
    }

    public interface onItemClickCallback {
        void onItemClicked(UserGithub data);
    }
}
