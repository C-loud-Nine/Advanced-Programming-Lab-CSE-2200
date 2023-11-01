package com.example.a2test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UnmarriedPeopleActivity extends AppCompatActivity {
    private ListView unmarriedPeopleListView;
    private ArrayList<String> unmarriedPeople = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unmarried_people);
        unmarriedPeopleListView = findViewById(R.id.unmarriedPeopleList);


        String unmarriedPeopleJson = getIntent().getStringExtra("unmarriedPeople");

        try {
            JSONArray unmarriedPeopleArray = new JSONArray(unmarriedPeopleJson);
            for (int i = 0; i < unmarriedPeopleArray.length(); i++) {
                String personName = unmarriedPeopleArray.getJSONObject(i).getString("name");
                unmarriedPeople.add(personName);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unmarriedPeople);
        unmarriedPeopleListView.setAdapter(adapter);

        unmarriedPeopleListView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                JSONArray unmarriedPeopleArray = new JSONArray(unmarriedPeopleJson);
                JSONObject selectedPerson = unmarriedPeopleArray.getJSONObject(position);

                JSONArray hobbies = selectedPerson.getJSONArray("hobbies");
                Intent intent = new Intent(UnmarriedPeopleActivity.this, Hobby.class);
                intent.putExtra("hobbies", hobbies.toString());
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }


}
