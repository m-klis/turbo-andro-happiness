package com.example.githubuser.activity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubuser.model.UserGithub;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<UserGithub>> listUsers = new MutableLiveData<>();

    void setUsers(final String users) {
        final ArrayList<UserGithub> listItems = new ArrayList<>();
        String token = "e1bb41a9221fe810f44ff5152ab3539a0470c5ab";
        final String url = "https://api.github.com/search/users?q=" + users;

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", token);
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray items = responseObject.getJSONArray("items");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject user = items.getJSONObject(i);
                        UserGithub userGithub = new UserGithub();
                        userGithub.setAvtr(user.getString("avatar_url"));
                        userGithub.setUser(user.getString("login"));
                        listItems.add(userGithub);
                    }
                    listUsers.postValue(listItems);
                } catch (Exception e) {
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
