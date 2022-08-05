package com.example.githubuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Users> users = new ArrayList<>();

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public UserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Users user = (Users) getItem(i);
        viewHolder.bind(user);
        return itemView;
    }

    private class ViewHolder{
        private TextView txtName, txtUser, txtLoca;
        private ImageView imgPict;

        ViewHolder(View view){
            txtName = view.findViewById(R.id.name);
            txtUser = view.findViewById(R.id.user);
            txtLoca = view.findViewById(R.id.loca);
            imgPict = view.findViewById(R.id.pict);
        }

        void bind(Users user){
            txtName.setText(user.getName());
            txtUser.setText(user.getUser());
            txtLoca.setText(user.getLoca());
            imgPict.setImageResource(user.getPict());
        }
    }
}
