package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private String[] dataName, dataUser, dataLoca, dataRepo, dataComp, dataFlwr, dataFlng;
    private TypedArray dataPict;
    private ArrayList<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listUser = findViewById(R.id.lv_list);
        adapter = new UserAdapter(this);
        listUser.setAdapter(adapter);

        prepare();
        addItem();

        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MainActivity.this, users.get(i).getName(), Toast.LENGTH_SHORT).show();
                Users user = new Users();
                user.setPict(users.get(i).getPict());
                user.setUser(users.get(i).getUser());
                user.setName(users.get(i).getName());
                user.setComp(users.get(i).getComp());
                user.setRepo(users.get(i).getRepo());
                user.setLoca(users.get(i).getLoca());
                user.setFlwr(users.get(i).getFlwr());
                user.setFlng(users.get(i).getFlng());

                Intent detailUser = new Intent(MainActivity.this, DetailUser.class);
                detailUser.putExtra(DetailUser.EXTRA_USER, user);
                startActivity(detailUser);
            }
        });
    }

    private void prepare(){
        dataUser = getResources().getStringArray(R.array.data_user);
        dataName = getResources().getStringArray(R.array.data_name);
        dataLoca = getResources().getStringArray(R.array.data_location);
        dataRepo = getResources().getStringArray(R.array.data_repository);
        dataComp = getResources().getStringArray(R.array.data_company);
        dataFlwr = getResources().getStringArray(R.array.data_followers);
        dataFlng = getResources().getStringArray(R.array.data_following);
        dataPict = getResources().obtainTypedArray(R.array.data_picture);
    }

    private void addItem() {
        users = new ArrayList<>();

        for(int i=0;i<dataName.length;i++){
            Users user = new Users();
            user.setPict(dataPict.getResourceId(i, -1));
            user.setUser(dataUser[i]);
            user.setName(dataName[i]);
            user.setLoca(dataLoca[i]);
            user.setRepo(dataRepo[i]);
            user.setComp(dataComp[i]);
            user.setFlwr(dataFlwr[i]);
            user.setFlng(dataFlng[i]);
            users.add(user);
        }

        adapter.setUsers(users);
    }
}
