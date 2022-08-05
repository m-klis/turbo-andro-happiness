package com.example.githubuser.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.githubuser.R;
import com.example.githubuser.fragment.FollowerFragment;
import com.example.githubuser.fragment.FollowingFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SectionPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_flwr,
            R.string.tab_flng
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = FollowerFragment.newInstance(userName);
                break;
            case 1:
                fragment = FollowingFragment.newInstance(userName);
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
