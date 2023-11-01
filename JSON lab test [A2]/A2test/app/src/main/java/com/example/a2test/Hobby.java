package com.example.a2test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Hobby extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        textView=findViewById(R.id.hobby);
        Intent intent=getIntent();

        String addddd = intent.getStringExtra("hobbies");

        textView.setText(addddd);
    }
}