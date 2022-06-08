package com.example.in_class_demo.InClass04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class HeavyWork implements Runnable {
    static final int COUNT = 900000;
    private final int numberCount;
    private static Handler messageQueue;
    public final static int STATUS_START = 0x001;
    public final static int STATUS_PROGRESS = 0x002;
    public final static int STATUS_END = 0x003;
    public final static int STATUS_RESULT = 0x004;
    public final static String KEY_PROGRESS = "KEY_PROGRESS";
    public final static String KEY_RESULT = "KEY_RESULT";

    private static int progress = 0;

    public HeavyWork(int n, Handler messageQueue) {
        this.numberCount = n;
        this.messageQueue = messageQueue;
    }

    static ArrayList<Double> getArrayNumbers(int n){
        ArrayList<Double> returnArray = new ArrayList<>();

        for (int i=0; i<n; i++){
            returnArray.add(getNumber());
//            Log.d("InClass04", "Added " + returnArray.get(i));
            progress++;
            Message progressMessage = new Message();
            Bundle bundleProgress = new Bundle();
            bundleProgress.putInt(KEY_PROGRESS, progress);
            progressMessage.what = STATUS_PROGRESS;
            progressMessage.setData(bundleProgress);
            messageQueue.sendMessage(progressMessage);
        }

        progress = 0;
        return returnArray;
    }

    static double getNumber(){
        double num = 0;
        Random rand = new Random();
        for(int i=0;i<COUNT; i++){
            num = num + rand.nextDouble();
        }
        return num / ((double) COUNT);
    }

    @Override
    public void run() {
        ArrayList<Double> numbers = getArrayNumbers(this.numberCount);
        Message startMessage = new Message();
        startMessage.what = STATUS_START;
        messageQueue.sendMessage(startMessage);

        double minimum = Collections.min(numbers);
        double maximum = Collections.max(numbers);
        double total = 0;
        for (int i = 0; i < numbers.size(); i++) {
            total += numbers.get(i);
        }
        double average = total / numbers.size();

        double[] results = new double[] {minimum, maximum, average};
//        Log.d("InClass04", "Min = " + minimum);
//        Log.d("InClass04", "Max = " + maximum);
//        Log.d("InClass04", "Avg = " + average);

        Message resultMessage = new Message();
        Bundle bundleResult = new Bundle();
        bundleResult.putDoubleArray(KEY_RESULT, results);
        resultMessage.what = STATUS_RESULT;
        resultMessage.setData(bundleResult);
        messageQueue.sendMessage(resultMessage);

        Message endMessage = new Message();
        endMessage.what = STATUS_END;
        messageQueue.sendMessage(endMessage);
    }
}