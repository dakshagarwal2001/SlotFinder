package com.example.slottracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class DistrictActivity extends AppCompatActivity {


    Context context;
    ArrayList<CenterInfo> al;
    Button button;
    EditText distID;
    EditText distDate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        context = this;
        al = new ArrayList<>();
        button = findViewById(R.id.distSubmit);
        distID = findViewById(R.id.distID);
        distDate = findViewById(R.id.distDate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = distID.getText().toString();
                String date = distDate.getText().toString();
                getDistSlots(id,date);
            }
        });

    }

    public void getDistSlots(String id,String date)
    {
        Log.d("mytag","in getPinSlots");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=" + id
                + "&date=" + date;
        Log.d("mytag",url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Log.d("mytag","in resonse");
                            JSONArray arr = response.getJSONArray("sessions");
                            int n = arr.length();
                            for(int i = 0;i<n;i++)
                            {
                                JSONObject obj = arr.getJSONObject(i);
                                String name = obj.getString("name");
                                int pin = obj.getInt("pincode");
                                int min_age_limit = obj.getInt("min_age_limit");
                                int slots = obj.getInt("available_capacity_dose1");
                                String vaccine = obj.getString("vaccine");
                                al.add(new CenterInfo(name,pin,min_age_limit,slots,vaccine));
                            }
                            Intent intent = new Intent(context,ListActivity.class);
//                            Intent intent = new Intent(context, .class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST",(Serializable)al);
                            intent.putExtra("BUNDLE",args);
                            startActivity(intent);

                        } catch (JSONException e) {
                            Toast.makeText(context, "Either District code or Date format is incorrect !! ", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mytag","some error ocurred ! ");
                Toast.makeText(context, "Either Pin format or Date format is incorrect !! ", Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}