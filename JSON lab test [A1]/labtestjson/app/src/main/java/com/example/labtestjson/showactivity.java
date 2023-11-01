package com.example.labtestjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class showactivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showactivity);

        textView=findViewById(R.id.textshow);
        Intent intent=getIntent();

        String addddd = intent.getStringExtra("address");

        textView.setText(addddd);
    }
}