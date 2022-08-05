package com.example.githubuser.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuser.R;
import com.example.githubuser.adapter.ListUserGithubAdapter;
import com.example.githubuser.model.UserGithub;

import java.util.ArrayList;
import java.util.Objects;

public class FollowerFragment extends Fragment {
    private ProgressBar progressBar;
    private ListUserGithubAdapter listUserGithubAdapter;
    private static final String USER_GITHUB = "user_github";
    private static final String FOLLOW = "follow";

    public static FollowerFragment newInstance(String user) {
        FollowerFragment followerFragment = new FollowerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USER_GITHUB, user);
        followerFragment.setArguments(bundle);
        return followerFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.follower_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.rv_flwr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listUserGithubAdapter = new ListUserGithubAdapter();
        listUserGithubAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listUserGithubAdapter);
        listUserGithubAdapter.setOnItemClickCallback(new ListUserGithubAdapter.onItemClickCallback() {
            @Override
            public void onItemClicked(UserGithub data) {
                Toast.makeText(getActivity(), data.getUser(), Toast.LENGTH_SHORT).show();
            }
        });

        String username = null;
        String follow = null;
        if (getArguments() != null) {
            username = getArguments().getString(USER_GITHUB);
            follow = getArguments().getString(FOLLOW);
        }

        FollowerViewModel followerViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity()), new ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel.class);
        followerViewModel.setFollow(username);
        followerViewModel.getUserGithub().observe(getActivity(), new Observer<ArrayList<UserGithub>>() {
            @Override
            public void onChanged(ArrayList<UserGithub> list) {
                if (list != null) {
                    listUserGithubAdapter.setData(list);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}