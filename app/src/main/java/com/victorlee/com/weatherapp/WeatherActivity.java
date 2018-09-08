package com.victorlee.com.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Receive the intent and unwrap the location intent
        String loc = this.getIntent().getStringExtra("location");
        Toast.makeText(this,loc,Toast.LENGTH_SHORT).show(); // pop up message shows
    }
}
