package com.example.in_class_demo.InClass05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.in_class_demo.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Nop Lertsumitkul
// Assignment #5

public class InClass05 extends AppCompatActivity {
    private EditText searchBar;
    private String searchQuery;
    private Button buttonGo;
    private ImageView buttonPrevious;
    private ImageView buttonNext;
    private ArrayList<String> imageUrls;
    private int imageIndex = 0;
    private ImageView mainImageView;
    private ProgressBar loadingSpinner;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);
        setTitle("Image Search");
        mainImageView = findViewById(R.id.mainImage);
        loadingSpinner = findViewById(R.id.loadingSpinner);


        buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                searchBar = findViewById(R.id.editTextSearch);
                searchQuery = searchBar.getText().toString();
                getImages();
            }
        });

        buttonPrevious = findViewById(R.id.previousButton);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limit = imageUrls.size();
                if (imageIndex == 0) {
                    imageIndex = limit - 1;
                } else {
                    imageIndex--;
                }
                getImages();
            }
        });

        buttonNext = findViewById(R.id.nextButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limit = imageUrls.size();
                if (imageIndex == limit - 1) {
                    imageIndex = 0;
                } else {
                    imageIndex++;
                }
                getImages();
            }
        });




    }

    private void getImages() {
        loadingSpinner.setVisibility(View.VISIBLE);
        mainImageView.setVisibility(View.INVISIBLE);
        buttonPrevious.setEnabled(false);
        buttonNext.setEnabled(false);
        final String[] urlString = new String[1];
        final HttpUrl[] url = {Objects.requireNonNull(HttpUrl.parse("http://dev.sakibnm.space/apis/images/retrieve")).newBuilder()
                .addQueryParameter("keyword", searchQuery).build()};
        Request request = new Request.Builder().url(url[0]).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Log.d("InClass05", "No Internet!");
                        Toast.makeText(InClass05.this, "Error! No Internet", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String body = response.body().string();
                    urlString[0] = body;
//                    Log.d("InClass05", "body is:\n" + urlString[0]);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageUrls = new ArrayList<>(Arrays.asList(urlString[0].split("\n")));
                            if (!imageUrls.get(imageIndex).isEmpty()) {
                                Picasso.get().load(imageUrls.get(imageIndex)).into(mainImageView);
                            } else {
                                mainImageView.setImageResource(android.R.color.transparent);
                                Toast.makeText(InClass05.this, "Error! No images found", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Log.d("InClass05", "Invalid Keyword!");
                            Toast.makeText(InClass05.this, "Invalid Keyword!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        loadingSpinner.setVisibility(View.INVISIBLE);
        mainImageView.setVisibility(View.VISIBLE);
        buttonPrevious.setEnabled(true);
        buttonNext.setEnabled(true);

    }

}