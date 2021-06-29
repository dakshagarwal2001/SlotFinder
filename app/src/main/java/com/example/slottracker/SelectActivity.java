package com.example.slottracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    Button pin;
    Button district;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        district = findViewById(R.id.district);
        pin = findViewById(R.id.pincode);
        Intent intentPin = new Intent(this,PinActivity.class);
        Intent intentDistrict = new Intent(this,DistrictActivity.class);
        pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPin);
            }
        });
        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentDistrict);
            }
        });
    }
}