//ITAMAR ZIK
//In Class 06
package com.example.in_class_demo.InClass06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.in_class_demo.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InClass06 extends AppCompatActivity {
//    private String[] stringArray = {"bing","bong","bong","bong","bong","bong","bong","bong","bong", "bong","bing", "bong","bing", "bong","bing", "bong"};
    private Articles articles;
    private ListView listView;
    private ArrayAdapter<Article> adapter;
    private EditText countryCodeInput;
    private EditText categoryInput;

    private Button getArticlesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class06);


        countryCodeInput = findViewById(R.id.countryCodeInput);
        categoryInput = findViewById(R.id.categoryInput);

        getArticlesButton = findViewById(R.id.getHeadlineButton);
        getArticlesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryCode = countryCodeInput.getText().toString();
                String category = categoryInput.getText().toString();

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://newsapi.org/v2/" +
                                "top-headlines?" +
                                "country=" + countryCode +
                                "&category="+ category +
                                "&apiKey=7b035f688d6441d4a81419f11c440e1a")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d("demo", "request failed");
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()){
//                    String string = response.body().string();
                            Gson gsonData = new Gson();
                            articles = gsonData.fromJson(response.body().charStream(), Articles.class);
                            Log.d("demo", "response:" + articles.toString());
                        }
                        else{
                            Log.d("demo", "response:" + response.body().string());
                        }
                    }
                });


                try {
                    Thread.sleep(10000);
                    Log.d("demo", "im up");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listView = findViewById(R.id.listView);


//                articles = new Articles();
//                getHeadlines(articles, countryCode, category);
                Log.d("demo", "fetching headlines...");
                Log.d("demo", articles.toString());



                adapter = new ArrayAdapter<>(InClass06.this, android.R.layout.simple_list_item_1, android.R.id.text1, articles.getHeadlines());
                listView.setAdapter(adapter);

            }
        });


    }

    private void getHeadlines(Articles articles, String country, String category){

        OkHttpClient client = new OkHttpClient();
//"https://newsapi.org/v2/top-headlines?" +
//        "country=us" +
//        "&category=sports" +
//        "&apiKey=7b035f688d6441d4a81419f11c440e1a"
        Request request = new Request.Builder()
                .url("https://newsapi.org/v2/" +
                        "top-headlines?" +
                        "country=" + country +
                        "&category="+ category +
                        "&apiKey=7b035f688d6441d4a81419f11c440e1a")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("demo", "request failed");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
//                    String string = response.body().string();
                    Gson gsonData = new Gson();
                    Articles fetched_articles = gsonData.fromJson(response.body().charStream(), Articles.class);
                    Log.d("demo", "response:" + fetched_articles.toString());
//                    articles.setHeadlines(fetched_articles.getHeadlines());
                }
                else{
                    Log.d("demo", "response:" + response.body().string());
                }
            }
        });

//        return articles;
    }







}