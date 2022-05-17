package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class selectAvatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        setTitle("Select Avatar");

        final int[] selectedAvatar = new int[1];

        ImageView avatar1 = findViewById(R.id.avatar2);
        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_f_1;
                Intent toMain = new Intent();
                toMain.putExtra("ToMain", selectedAvatar[0]);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        ImageView avatar2 = findViewById(R.id.avatar1);
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_f_2;
                Intent toMain = new Intent();
                toMain.putExtra("ToMain", selectedAvatar[0]);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        ImageView avatar3 = findViewById(R.id.avatar3);
        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_f_3;
                Intent toMain = new Intent();
                toMain.putExtra("ToMain", selectedAvatar[0]);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        ImageView avatar4 = findViewById(R.id.avatar4);
        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_m_1;
                Intent toMain = new Intent();
                toMain.putExtra("ToMain", selectedAvatar[0]);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        ImageView avatar5 = findViewById(R.id.avatar5);
        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_m_2;
                Intent toMain = new Intent();
                toMain.putExtra("ToMain", selectedAvatar[0]);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

        ImageView avatar6 = findViewById(R.id.avatar6);
        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_m_3;
                Intent toMain = new Intent();
                toMain.putExtra("ToMain", selectedAvatar[0]);
                setResult(RESULT_OK, toMain);
                finish();
            }
        });

    }
}