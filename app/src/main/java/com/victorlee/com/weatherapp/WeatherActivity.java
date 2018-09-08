package com.victorlee.com.weatherapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Receive the intent and unwrap the location intent
        String loc = this.getIntent().getStringExtra("location");
        Toast.makeText(this,loc,Toast.LENGTH_SHORT).show(); // pop up message shows

        // call this function getWeather(loc)
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String result = getWeather(loc);
            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getWeather(final String cityName)
            throws Exception {

        //Add your APPID here
        final String APPID = "65aec57a10da8acb4ebbeb30705f200b";

        //Construct and instantiate the URL
        final String REQUEST = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                cityName, APPID);
        final URL weatherURL = new URL(REQUEST);

        //Open the connection
        HttpURLConnection conn = (HttpURLConnection)weatherURL.openConnection();

        //Buffer for the incoming data
        byte[] buff = new byte[4 * 1024];

        //Open the stream for reading
        try (InputStream is = new BufferedInputStream(conn.getInputStream())) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //Create a 4K buffer to hold data
            int count = 0;
            //Read the data into the buffer, EOF = -1
            while ((count = is.read(buff)) != -1)
                bos.write(buff, 0, count);
            bos.flush();
            buff = bos.toByteArray();
            bos.close();
        }

        //Use to hold the final result
        StringBuilder sb = new StringBuilder();

        JSONObject result = new JSONObject(new String(buff));
        JSONObject jObj = result.getJSONObject("coord");

        //Get location attribute
        sb.append(String.format("Location: lat: %f, lon: %f\n",
                jObj.getDouble("lat"), jObj.getDouble("lon")));

        //Get the weather attribute
        JSONArray jArr = result.getJSONArray("weather");
        for (int i = 0; i < jArr.length(); i++) {
            jObj  = jArr.getJSONObject(i);
            sb.append(String.format("Weather: %s - %s\n",
                    jObj.getString("main"), jObj.getString("description")));
        }

        //Get the temperate attribute
        jObj = result.getJSONObject("main");
        sb.append(String.format("Temperature: %f\n", jObj.getDouble("temp")));
        sb.append(String.format("Min temp: %f\n", jObj.getDouble("temp_min")));
        sb.append(String.format("Max temp: %f\n", jObj.getDouble("temp_max")));

        //Return the result
        return (sb.toString());
    }


}
