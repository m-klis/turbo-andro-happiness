package com.example.githubuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailUser extends AppCompatActivity{
    public static final String EXTRA_USER = "extra_user";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_user);
        TextView tvUser = findViewById(R.id.tv_user);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvLoca = findViewById(R.id.tv_loca);
        TextView tvRepo = findViewById(R.id.tv_repo);
        TextView tvComp = findViewById(R.id.tv_comp);
        TextView tvFlwr = findViewById(R.id.tv_flwr);
        TextView tvFlng = findViewById(R.id.tv_flng);
        ImageView tvPict = findViewById(R.id.tv_pict);
        TextView btnBack = findViewById(R.id.btn_back);

        Users user = getIntent().getParcelableExtra(EXTRA_USER);

        if(user != null){
            tvUser.setText(user.getUser());
            tvName.setText(user.getName());
            tvLoca.setText(user.getLoca());
            tvRepo.setText(user.getRepo());
            tvComp.setText(user.getComp());
            tvFlwr.setText(user.getFlwr());
            tvFlng.setText(user.getFlng());
            tvPict.setImageResource(user.getPict());
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(DetailUser.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
}
