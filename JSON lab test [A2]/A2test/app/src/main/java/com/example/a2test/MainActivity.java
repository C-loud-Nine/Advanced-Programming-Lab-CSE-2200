package com.example.a2test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2test.UnmarriedPeopleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView companyListView;
    private ArrayList<String> companyNames = new ArrayList<>();
    private JSONObject jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyListView = findViewById(R.id.list);


        parseJson();

        companyListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCompany = companyNames.get(position);


            Intent intent = new Intent(MainActivity.this, UnmarriedPeopleActivity.class);
            try {
                JSONArray data = jsonData.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject company = data.getJSONObject(i);
                    String companyName = company.getString("company");
                    if (companyName.equals(selectedCompany)) {
                        JSONArray people = company.getJSONArray("people");
                        JSONArray unmarriedPeople = new JSONArray();

                        for (int j = 0; j < people.length(); j++) {
                            JSONObject person = people.getJSONObject(j);
                            boolean isMarried = person.getBoolean("isMarried");
                            if (!isMarried) {
                                unmarriedPeople.put(person);
//                                JSONArray hobi= company.getJSONArray("hobbies");
//                                JSONArray hobby = new JSONArray();
//                                for (int k = 0; k < hobi.length(); k++){
//
//                                }
//

//

                            }
                        }


                        intent.putExtra("unmarriedPeople", unmarriedPeople.toString());
                        startActivity(intent);
                        return;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void parseJson() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.online/v1/records/53b4282e-3ea7-4c05-b9b2-b9825c0c91f0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonData = response;
                        try {
                            JSONArray data = response.getJSONArray("data");

                            for (int i = 0; i < data.length(); i++) {
                                JSONObject company = data.getJSONObject(i);
                                String companyName = company.getString("company");
                                companyNames.add(companyName);
                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, companyNames);
                            companyListView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
