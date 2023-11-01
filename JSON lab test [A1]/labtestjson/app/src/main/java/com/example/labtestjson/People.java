package com.example.labtestjson;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class People extends AppCompatActivity {
ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        listView=findViewById(R.id.list);


        Intent intent = getIntent();

        String name = intent.getStringExtra("company");






    }
}