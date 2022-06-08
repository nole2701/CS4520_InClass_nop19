package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.in_class_demo.InClass01.InClass01;
import com.example.in_class_demo.InClass02.InClass02;
import com.example.in_class_demo.InClass03.InClass03;
import com.example.in_class_demo.InClass04.InClass04;
import com.example.in_class_demo.InClass05.InClass05;
import com.example.in_class_demo.InClass06.InClass06;
import com.example.in_class_demo.Practice.Practice;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG1 = "demo";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG1, "Hello World");
//        Toast.makeText(this, "Showing Demo Toast!", Toast.LENGTH_LONG).show();
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

        Button buttonInClass02 = findViewById(R.id.buttonInClass02);
        buttonInClass02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass02 = new Intent(MainActivity.this, InClass02.class);
                startActivity(toInClass02);
            }
        });

        Button buttonInClass03 = findViewById(R.id.buttonInClass03);
        buttonInClass03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass03 = new Intent(MainActivity.this, InClass03.class);
                startActivity(toInClass03);
            }
        });

        Button buttonInClass04 = findViewById(R.id.buttonInClass04);
        buttonInClass04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass04 = new Intent(MainActivity.this, InClass04.class);
                startActivity(toInClass04);
            }
        });

        Button buttonInClass05 = findViewById(R.id.buttonInClass05);
        buttonInClass05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass05 = new Intent(MainActivity.this, InClass05.class);
                startActivity(toInClass05);
            }
        });

        Button buttonInClass06 = findViewById(R.id.buttonInClass06);
        buttonInClass06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass06 = new Intent(MainActivity.this, InClass06.class);
                startActivity(toInClass06);
            }
        });

        Button buttonInClass07 = findViewById(R.id.buttonInClass07);
        buttonInClass07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent toInClass07 = new Intent(MainActivity.this, InClass07.class);
                startActivity(toInClass07);
            }
        });
    }
}