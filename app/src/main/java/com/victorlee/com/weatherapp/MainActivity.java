package com.victorlee.com.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayAdapter<String> myAdapter; // Use Adapter to pass data to ListView
    public ArrayList<String> dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This will be called when you launch this activity

        dataSource = new ArrayList<String>();
        dataSource.add("Singapore");
        dataSource.add("Malaysia");

        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSource);

        ListView lv = (ListView) findViewById(R.id.location_lv);
        lv.setAdapter( myAdapter);

        // Set listener for item click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                String location = tv.getText().toString();

                Intent i = new Intent(getApplicationContext(),WeatherActivity.class);
                i.putExtra("location",location);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addlocation){
            Toast.makeText(this,"You clicked the add button",Toast.LENGTH_SHORT).show();
            // Testing adding list
            dataSource.add("Vietnam");
            myAdapter.notifyDataSetChanged();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.button_menu,menu);
        return true;
    }
}
