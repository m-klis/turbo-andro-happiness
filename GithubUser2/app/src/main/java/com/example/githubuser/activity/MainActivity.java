package com.example.githubuser.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.githubuser.R;
import com.example.githubuser.adapter.ListUserGithubAdapter;
import com.example.githubuser.model.UserGithub;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListUserGithubAdapter listUserGithubAdapter;
    private ProgressBar progressBar;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchViewUser = findViewById(R.id.sv_user);
        progressBar = findViewById(R.id.progressBar);

        RecyclerView recyclerView = findViewById(R.id.rv_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listUserGithubAdapter = new ListUserGithubAdapter();
        listUserGithubAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listUserGithubAdapter);
        listUserGithubAdapter.setOnItemClickCallback(new ListUserGithubAdapter.onItemClickCallback() {
            @Override
            public void onItemClicked(UserGithub data) {
//                Toast.makeText(MainActivity.this, "Memilih " + data.getUser(), Toast.LENGTH_SHORT).show();
                Intent detailUser = new Intent(MainActivity.this, DetailActivity.class);
                detailUser.putExtra(DetailActivity.EXTRA_USERNAME, data.getUser());
                startActivity(detailUser);
            }
        });

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        searchViewUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showLoading(true);
                mainViewModel.setUsers(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listUserGithubAdapter.clearData();
                return false;
            }
        });

        mainViewModel.getUserGithub().observe(this, new Observer<ArrayList<UserGithub>>() {
            @Override
            public void onChanged(ArrayList<UserGithub> list) {
                listUserGithubAdapter.setData(list);
                showLoading(false);
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}