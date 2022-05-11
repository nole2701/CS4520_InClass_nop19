package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Practice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        setTitle("Practice In Class");

        Button buttonLogcat = findViewById(R.id.buttonLogcat);
        buttonLogcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Log.d("Practice", "Practice Practice Practice!!!");
            }
        });

        Button buttonToast = findViewById(R.id.buttonToast);
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Toast.makeText(Practice.this, "Now push to GitHub and Submit!", Toast.LENGTH_LONG).show();
            }
        });
    }
}