package com.victorlee.com.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLocationActivity extends AppCompatActivity {

    EditText location_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        // Create reference to EditText, and two buttons
        location_editText = (EditText) findViewById(R.id.location_editText);

        // Alt Enter to auto import
        Button add_btn = (Button) findViewById(R.id.btn_add);
        Button cancel_btn = (Button) findViewById(R.id.btn_cancel);

        add_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String loc = location_editText.getText().toString();
                Toast.makeText(getApplicationContext(),loc,Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
                resultIntent.putExtra("location",loc);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);

                setResult(Activity.RESULT_CANCELED,resultIntent);
                finish();

            }
        });
    }


}
