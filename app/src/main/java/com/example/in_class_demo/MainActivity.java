package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG1 = "demo";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG1, "Hello World");
        Toast.makeText(this, "Showing Demo Toast!", Toast.LENGTH_LONG).show();
        Log.d(TAG1, "toast shown");

        Button buttonPractice = findViewById(R.id.buttonPractice);
        buttonPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toPractice = new Intent(MainActivity.this, Practice.class);
                startActivity(toPractice);
            }
        });

        Button buttonInClass01 = findViewById(R.id.buttonInClass01);
        buttonInClass01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass01 = new Intent(MainActivity.this, InClass01.class);
                startActivity(toInClass01);
            }
        });
    }
}