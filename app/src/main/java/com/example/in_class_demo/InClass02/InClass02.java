package com.example.in_class_demo.InClass02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.in_class_demo.R;

// Nop Lertsumitkul
// Assignment #2

public class InClass02 extends AppCompatActivity {
    ImageView avatarImage;
    RadioGroup radioGroupPlatform;
    RadioButton selectedButton;
    String iUseString;
    SeekBar moodBar;
    String moodString;
    ImageView moodImage;
    String name;
    String email;
    int avatarFileID;
    int moodFileID;
    final static String nameKey = "nameKey";
    final static String emailKey = "emailKey";
    final static String moodStringKey = "moodStringKey";
    final static String moodImageKey = "moodImageKey";
    final static String avatarImageKey = "avatarImageKey";
    final static String iUseKey = "iUseKey";

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            avatarImage = findViewById(R.id.avatarImage);
            if(result.getResultCode() == RESULT_OK) {
                assert result.getData() != null;
                int selectedAvatar = result.getData().getIntExtra("ToMain", 0);
                avatarImage.setImageResource(selectedAvatar);
                avatarFileID = selectedAvatar;
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class02);
        setTitle("Edit Profile Activity");


        ImageView avatarImage = findViewById(R.id.avatarImage);
        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                Intent selectAvatar = new Intent(InClass02.this, selectAvatar.class);
                startActivityForResult.launch(selectAvatar);
            }
        });

        moodString = "Angry";
        moodFileID = R.mipmap.angry;

        moodBar = findViewById(R.id.moodSeekBar);
        moodImage = findViewById(R.id.moodImage);
        moodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(progress) {
                    case 0:
                        moodImage.setImageResource(R.mipmap.angry);
                        moodString = "Angry";
                        moodFileID = R.mipmap.angry;
                        break;
                    case 1:
                        moodImage.setImageResource(R.mipmap.sad);
                        moodString = "Sad";
                        moodFileID = R.mipmap.sad;
                        break;
                    case 2:
                        moodImage.setImageResource(R.mipmap.happy);
                        moodString = "Happy";
                        moodFileID = R.mipmap.happy;
                        break;
                    case 3:
                        moodImage.setImageResource(R.mipmap.awesome);
                        moodString = "Awesome";
                        moodFileID = R.mipmap.awesome;
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...

                name = ((TextView)findViewById(R.id.editTextTextName)).getText().toString();
                email = ((TextView)findViewById(R.id.editTextTextEmail)).getText().toString();


                radioGroupPlatform = findViewById(R.id.radioGroupPlatform);
                int selectedButtonID = radioGroupPlatform.getCheckedRadioButtonId();
                if (selectedButtonID != -1) {
                    selectedButton = findViewById(selectedButtonID);
                    iUseString = selectedButton.getText().toString();
                }
                Intent submitActivity = new Intent(InClass02.this, submitActivity.class);
                submitActivity.putExtra(nameKey, name);
                submitActivity.putExtra(emailKey, email);
                submitActivity.putExtra(moodStringKey, moodString);
                submitActivity.putExtra(iUseKey, iUseString);
                submitActivity.putExtra(moodImageKey, moodFileID);
                submitActivity.putExtra(avatarImageKey, avatarFileID);
                startActivity(submitActivity);


            }
        });

    }
}