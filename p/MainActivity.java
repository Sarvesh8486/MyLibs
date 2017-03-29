package com.example.sarveshtank.securenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sarveshtank.securenotes.util.UserListAdapters;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserListAdapters adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_heading);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        adapters = new UserListAdapters(null);
        recyclerView.setAdapter(adapters);
    }


}
