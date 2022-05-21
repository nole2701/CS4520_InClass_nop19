package com.example.in_class_demo.InClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.in_class_demo.R;

public class submitActivity extends AppCompatActivity {


    String name;
    String email;
    String iUseString = "nothing???";
    String moodString;
    TextView nameResult;
    TextView emailResult;
    TextView iUseResult;
    TextView moodResult;
    ImageView moodImage;
    ImageView avatarImage;
    int moodImageResource;
    int avatarImageResource;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        setTitle("Display Activity");

        if (getIntent() != null && getIntent().getExtras() != null) {
            name = getIntent().getStringExtra(InClass02.nameKey);
            email = getIntent().getStringExtra(InClass02.emailKey);
            iUseString = getIntent().getStringExtra(InClass02.iUseKey);
            moodString = getIntent().getStringExtra(InClass02.moodStringKey);
            moodImageResource = getIntent().getIntExtra(InClass02.moodImageKey, 0);
            avatarImageResource = getIntent().getIntExtra(InClass02.avatarImageKey, R.mipmap.select_avatar);

            nameResult = findViewById(R.id.nameResult);
            emailResult = findViewById(R.id.emailResult);
            iUseResult = findViewById(R.id.iUseView);
            moodResult = findViewById(R.id.iAmMood);
            moodImage = findViewById(R.id.moodImageFinal);
            avatarImage = findViewById(R.id.avatarFinal);

            nameResult.setText("Name: " + name);
            emailResult.setText("Email: " + email);
            iUseResult.setText("I use: " + iUseString + "!");
            moodResult.setText("I am: " + moodString + "!");
            moodImage.setImageResource(moodImageResource);
            avatarImage.setImageResource(avatarImageResource);


        }
    }
}