package com.example.in_class_demo.InClass03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_class_demo.R;

import java.util.Objects;

// Nop Lertsumitkul
// Assignment #3

public class InClass03 extends AppCompatActivity implements selectAvatarFragment.IFromFragmentToActivity {
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

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void fromFragment(int avatarID) {
//        Toast.makeText(this, "Returned data from fragment", Toast.LENGTH_LONG).show();
        avatarImage = findViewById(R.id.avatarImage);
        avatarImage.setImageResource(avatarID);
        avatarFileID = avatarID;
        Fragment selectAvatarFragment = getSupportFragmentManager().findFragmentByTag("selectAvatarFragment");
        getSupportFragmentManager().beginTransaction().remove(selectAvatarFragment).commit();
        setTitle("Edit Profile Activity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class03);
        setTitle("Edit Profile Activity");

        Fragment selectAvatarFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentSelectAvatar);
        getSupportFragmentManager().beginTransaction().remove(selectAvatarFragment).commit();
        Fragment submitActivityFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentSubmitActivity);
        getSupportFragmentManager().beginTransaction().remove(submitActivityFragment).commit();

        ImageView avatarImage = findViewById(R.id.avatarImage);
        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
//                Toast.makeText(InClass03.this, "Opening selection", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.containerFragmentSelectAvatar, new selectAvatarFragment(), "selectAvatarFragment")
                        .addToBackStack(null).commit();
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

                boolean nameFlag = Objects.equals(name, "");
                boolean emailFlag = !isEmailValid(email);
                boolean iUseStringFlag = !Objects.equals(iUseString, "Android") && !Objects.equals(iUseString, "iOS");
                String toastMessage = "";
                if (nameFlag) {
                    toastMessage += "Name, ";
                }
                if (emailFlag) {
                    toastMessage += "Email, ";
                }
                if (iUseStringFlag) {
                    toastMessage += "Device Select";
                }

                if (!nameFlag && !emailFlag && !iUseStringFlag) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.containerFragmentSubmitActivity, com.example.in_class_demo.InClass03.submitActivityFragment.newInstance(name,
                                    email, moodString, iUseString, moodFileID, avatarFileID), "selectAvatarFragment")
                            .addToBackStack(null).commit();
                    setTitle("Display Activity");
                } else {
                    Toast.makeText(InClass03.this, "Invalid inputs for: " + toastMessage, Toast.LENGTH_LONG).show();
                }




            }
        });

    }

}