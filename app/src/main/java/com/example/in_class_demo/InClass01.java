package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InClass01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class01);
        setTitle("BMI Calculator");
        final String TAG1 = "InClass01";



        Button buttonCalculate = findViewById(R.id.buttonCalculate);
//        String displayCategoryBMI = categoryBMI;
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                EditText weightEditText = findViewById(R.id.editTextWeight);
                String weightInput = String.valueOf(weightEditText.getText());
                Log.d(TAG1, "Weight input received as: " + weightInput);

                EditText heightFeetEditText = findViewById(R.id.editTextHeight1);
                EditText heightInchesEditText = findViewById(R.id.editTextHeight2);
                String heightFeetInput = String.valueOf(heightFeetEditText.getText());
                String heightInchesInput = String.valueOf(heightInchesEditText.getText());
                Log.d(TAG1, "Height feet input received as: " + heightFeetInput);
                Log.d(TAG1, "Height inches input received as: " + heightInchesInput);


                double weightPounds = Double.parseDouble(weightInput);
                double heightInches = Double.parseDouble(heightFeetInput) * 12
                        + Double.parseDouble((heightInchesInput));

                if ((weightPounds < 0 || Double.parseDouble(heightFeetInput) < 0
                    || Double.parseDouble(heightInchesInput) < 0)) {
                    TextView resultsTextView = findViewById(R.id.textViewResults);
                    String invalidInputText = "Invalid Input";
                    resultsTextView.setText(invalidInputText);
                    return;
                }

                double rawBMI = weightPounds / (heightInches*heightInches) * 703;
                double BMI = Math.round(rawBMI*100.0)/100.0;
                String categoryBMI = "";
                if (BMI < 18.5) {
                    categoryBMI = "Your BMI: " + BMI + "\n You are underweight";
                }
                if (BMI >= 18.5 && BMI < 24.9) {
                    categoryBMI = "Your BMI: " + BMI + "\n You are normal weight";
                }
                if (BMI >= 24.9 && BMI <= 29.9) {
                    categoryBMI = "Your BMI: " + BMI + "\n You are overweight";
                }
                if (BMI > 29.9) {
                    categoryBMI = "Your BMI: " + BMI + "\n You are obese";
                }
                Log.d(TAG1, categoryBMI);

                TextView resultsTextView = findViewById(R.id.textViewResults);
                resultsTextView.setText(categoryBMI);
            }
        });
    }
}