package com.example.githubuser.fragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubuser.model.UserGithub;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class FollowingViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<UserGithub>> listUsers = new MutableLiveData<>();

    void setFollow(String user) {
        final ArrayList<UserGithub> listItem = new ArrayList<>();
        String apiKey = "28d1c9e4307f4c6661560ac6281b451a";
        String url = "https://api.github.com/users/" + user + "/following";

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", apiKey);
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONArray response = new JSONArray(result);

                    for (int i=0; i<response.length();i++){
                        JSONObject user = response.getJSONObject(i);
                        UserGithub userGithub = new UserGithub();
                        userGithub.setAvtr(user.getString("avatar_url"));
                        userGithub.setUser(user.getString("login"));
                        listItem.add(userGithub);
                    }
                    listUsers.postValue(listItem);
                }catch (Exception e) {
                    Log.d("Exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    LiveData<ArrayList<UserGithub>> getUserGithub() {
        return listUsers;
    }
}