package com.example.githubuser.activity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubuser.model.UserGithub;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<UserGithub> dataUser = new MutableLiveData<>();

    void setData(String username) {
        String url = "https://api.github.com/users/" + username;
        String apiKey = "28d1c9e4307f4c6661560ac6281b451a";

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", apiKey);
        client.addHeader("User-Agent", "request");
        RequestHandle requestHandle = client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    UserGithub userGithub = new UserGithub();
                    String response = new String(responseBody);
                    JSONObject result = new JSONObject(response);
                    userGithub.setUser(result.getString("login"));
                    userGithub.setName(result.getString("name"));
                    userGithub.setFlwr(result.getString("followers"));
                    userGithub.setFlng(result.getString("following"));
                    userGithub.setRepo(result.getString("public_repos"));
                    userGithub.setComp(result.getString("company"));
                    userGithub.setLoca(result.getString("location"));
                    userGithub.setAvtr(result.getString("avatar_url"));
                    dataUser.postValue(userGithub);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<UserGithub> getData() {
        return dataUser;
    }
}
