package com.example.labtestjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class peopleactivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> names = new ArrayList<String>() ;
    ArrayList<String> address = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peopleactivity);

        listView=findViewById(R.id.list);


        Intent intent = getIntent();
        String company11 = intent.getStringExtra("company").toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.online/v1/records/53b4282e-3ea7-4c05-b9b2-b9825c0c91f0";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                 //   for(int i=0; i<jsonArray.length(); i++)
                //    {
                     //   JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        //String company = jsonObject1.getString("company");
                        if(company11.equals("Grameenphone"))
                        {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                            JSONArray people = jsonObject1.getJSONArray("people");
                           for(int j=0; j<people.length(); j++)
                           {
                               JSONObject pep = people.getJSONObject(j);
                               JSONObject add = pep.getJSONObject("address");

                               String a= add.getString("city");
                               String b = add.getString("street");
                               String c = add.getString("zipCode");

                               address.add("City : "+a+" street : "+b+" zipcode : "+ c);
                               String name = pep.getString("name");
                               names.add(name);

                               ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
                               listView.setAdapter(arrayAdapter);

                               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                   @Override
                                   public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                                       Intent intent1 = new Intent(peopleactivity.this, showactivity.class);

                                       intent1.putExtra("address", address.get(i));


                                       startActivity(intent1);
                                      // i=3;
                                   }
                               });
                           }


                        }
                        //JSONObject robi = jsonArray.getJSONObject(1);
                        if(company11.equals("Robi"))
                        {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(1);
                            JSONArray people = jsonObject1.getJSONArray("people");
                            for(int j=0; j<people.length(); j++)
                            {
                                JSONObject pep = people.getJSONObject(j);
                                JSONObject add = pep.getJSONObject("address");

                                String a= add.getString("city");
                                String b = add.getString("street");
                                String c = add.getString("zipCode");

                                address.add("City : "+a+" street : "+b+" zipcode : "+ c);
                                String name = pep.getString("name");
                                names.add(name);

                                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
                                listView.setAdapter(arrayAdapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                                        Intent intent1 = new Intent(peopleactivity.this, showactivity.class);

                                        intent1.putExtra("address", address.get(i));


                                        startActivity(intent1);
                                    //    i=3;
                                    }
                                });

                            }

                        }
                        if(company11.equals("Banglalink"))
                        {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(2);
                            JSONArray people = jsonObject1.getJSONArray("people");
                            for(int j=0; j<people.length(); j++)
                            {
                                JSONObject pep = people.getJSONObject(j);
                                JSONObject add = pep.getJSONObject("address");

                                String a= add.getString("city");
                                String b = add.getString("street");
                                String c = add.getString("zipCode");

                                address.add("City : "+a+" street : "+b+" zipcode : "+ c);
                                String name = pep.getString("name");
                                names.add(name);

                                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
                                listView.setAdapter(arrayAdapter);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                                        Intent intent1 = new Intent(peopleactivity.this, showactivity.class);

                                        intent1.putExtra("address", address.get(i));


                                        startActivity(intent1);
                                       // i=3;
                                    }
                                });
                            }


                        }

                 //   }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);

    }
}