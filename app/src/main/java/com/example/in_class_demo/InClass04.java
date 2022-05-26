package com.example.in_class_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_class_demo.InClass03.InClass03;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InClass04 extends AppCompatActivity {
    private Button generateButton;
    private ExecutorService threadPool;
    private TextView complexityValue;
    private TextView valueMin;
    private TextView valueMax;
    private TextView valueAvg;
    private Handler messageQueue;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class04);
        setTitle("Number Generator");
        threadPool = Executors.newFixedThreadPool(2);
        complexityValue = findViewById(R.id.valueComplexity);
        complexityValue.setText("8");
        progressBar = findViewById(R.id.progressBar);



        SeekBar complexityBar = findViewById(R.id.seekBar);
        final int[] complexity = new int[1];
        complexity[0] = 8;
        complexityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                complexity[0] = progress;
                complexityValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        messageQueue = new Handler(new Handler.Callback() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch(msg.what) {
                    case HeavyWork.STATUS_START:
//                        Log.d("InClass04", "Worker is starting");
                        break;
                    case HeavyWork.STATUS_END:
//                        Log.d("InClass04", "Worker is ending");
                        break;
                    case HeavyWork.STATUS_PROGRESS:
                        Bundle receivedData = msg.getData();
                        int donePercent = (receivedData.getInt(HeavyWork.KEY_PROGRESS)*100) / complexity[0];
//                        Log.d("InClass04", donePercent+"% of work done");
                        progressBar.setProgress(donePercent);
                        double alphaValue = donePercent*2.5;
                        progressBar.setAlpha((float)alphaValue);
                        if (donePercent == 100) {
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                        break;
                    case HeavyWork.STATUS_RESULT:
                        Bundle resultData = msg.getData();
                        double[] resultArray = resultData.getDoubleArray(HeavyWork.KEY_RESULT);
                        valueMin = findViewById(R.id.valueMin);
                        valueMax = findViewById(R.id.valueMax);
                        valueAvg = findViewById(R.id.valueAvg);
                        DecimalFormat df = new DecimalFormat("#.######");
                        String stringMin = String.valueOf(df.format(resultArray[0]));
                        String stringMax = String.valueOf(df.format(resultArray[1]));
                        String stringAvg = String.valueOf(df.format(resultArray[2]));
                        valueMin.setText(stringMin);
                        valueMax.setText(stringMax);
                        valueAvg.setText(stringAvg);
                }
                return false;
            }
        });
        generateButton = findViewById(R.id.buttonGenerate);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                threadPool.execute(new HeavyWork(complexity[0], messageQueue));



            }
        });

    }
}