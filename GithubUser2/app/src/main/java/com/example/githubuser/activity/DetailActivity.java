package com.example.githubuser.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubuser.R;
import com.example.githubuser.adapter.SectionPagerAdapter;
import com.example.githubuser.model.UserGithub;
import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "username";
    private ProgressBar progressBar;
    private TextView tvName, tvUser, tvFlwr, tvFlng, tvRepo, tvComp, tvLoca;
    private ImageView imgAvtr;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);
        imgAvtr = findViewById(R.id.img_avtr);
        tvUser = findViewById(R.id.tv_user);
        tvFlwr = findViewById(R.id.tv_flwr);
        tvFlng = findViewById(R.id.tv_flng);
        tvRepo = findViewById(R.id.tv_repo);
        tvComp = findViewById(R.id.tv_comp);
        tvLoca = findViewById(R.id.tv_loca);
        tvName = findViewById(R.id.tv_name);

        userName = getIntent().getStringExtra(EXTRA_USERNAME);

        DetailViewModel detailViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);
        detailViewModel.setData(userName);
        detailViewModel.getData().observe(this, new Observer<UserGithub>() {
            @Override
            public void onChanged(UserGithub userGithub) {
                if (userGithub.getUser() != null) {
                    Glide.with(DetailActivity.this)
                            .load(userGithub.getAvtr())
                            .apply(new RequestOptions().override(55, 55))
                            .into(imgAvtr);
                    tvUser.setText(userGithub.getUser());
                    tvFlwr.setText(userGithub.getFlwr());
                    tvFlng.setText(userGithub.getFlng());
                    tvRepo.setText(userGithub.getRepo());
                    tvComp.setText(userGithub.getComp());
                    tvLoca.setText(userGithub.getLoca());
                    tvName.setText(userGithub.getName());
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager());
        sectionPagerAdapter.setUserName(userName);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabs = findViewById(R.id.tab_view);
        tabs.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);
    }
}