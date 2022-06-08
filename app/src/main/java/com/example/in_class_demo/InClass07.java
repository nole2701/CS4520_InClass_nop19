package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.in_class_demo.InClass02.InClass02;
import com.example.in_class_demo.InClass02.submitActivity;
import com.example.in_class_demo.InClass05.InClass05;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InClass07 extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private Button buttonLogin;
    private String authToken;
    final static String authTokenKey = "authTokenKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07);
        setTitle("InClass07 - Notes App");
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("name", name)
                        .add("email", email)
                        .add("password", password)
                        .build();

                Request request = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/auth/register?" +
                                "name=" + name +
                                "&email=" + email +
                                "&password=" + password).post(formBody).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("InClass07", "request failed for some reason");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String string = null;
                                    try {
                                        string = response.body().string();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    JSONObject rootJson = null;
                                    try {
                                        rootJson = new JSONObject(string);
                                        authToken = rootJson.getString("token");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Log.d("InClass07", "response authToken: " + authToken);
                                    Intent NotesPage = new Intent(InClass07.this, NotesPage.class);
                                    NotesPage.putExtra(authTokenKey, authToken);
                                    startActivity(NotesPage);
                                }
                            });

                        } else {
                            Log.d("InClass07", "response:" + response.body().string());
                        }
                    }
                });
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("email", email)
                        .add("password", password)
                        .build();

                Request request = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/auth/login?" +
                                "email=" + email +
                                "&password=" + password).post(formBody).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("InClass07", "request failed for some reason");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String string = null;
                                    try {
                                        string = response.body().string();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    JSONObject rootJson = null;
                                    try {
                                        rootJson = new JSONObject(string);
                                        authToken = rootJson.getString("token");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Log.d("InClass07", "response authToken: " + authToken);
                                    Intent NotesPage = new Intent(InClass07.this, NotesPage.class);
                                    NotesPage.putExtra(authTokenKey, authToken);
                                    startActivity(NotesPage);
                                }
                            });


                        } else {
                            Log.d("InClass07", "response:" + response.body().string());
                        }
                    }
                });

            }

        });

    }
}