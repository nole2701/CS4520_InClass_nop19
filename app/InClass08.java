package com.example.cs4520inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cs4520inclass.inclass07.LoginFragment;

public class InClass08 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class08);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView, new init_InClass08(), "init_fragment ")
                .commit();
    }
}