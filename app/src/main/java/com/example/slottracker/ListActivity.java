package com.example.slottracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<CenterInfo> object = (ArrayList<CenterInfo>) args.getSerializable("ARRAYLIST");
        ImageView img = findViewById(R.id.imageView2);
        TextView tv = findViewById(R.id.textView6);
        if(object.size()==0)
        {
            img.setImageResource(R.drawable.noslots);
            tv.setText("No slots available for given date !!");
        }
        else
        {
            Log.d("mytag","in list activity  " + object.size() + object.get(0).name);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            MyAdapter adapter = new MyAdapter(this,object);
            recyclerView.setAdapter(adapter);
        }
    }
}