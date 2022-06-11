package com.example.in_class_demo.InClass07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_class_demo.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotesPage extends AppCompatActivity {
    private String authToken;
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private TextView textViewDetails;
    private ArrayList<Note> notes;
    private String userID;
    private String userName;
    private String userEmail;
    private Button buttonAddNote;
    private Button buttonDeleteNote;
    private EditText addOrDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_page);
        setTitle("Notes Landing page");
        textViewDetails = findViewById(R.id.textViewDetails);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonDeleteNote = findViewById(R.id.buttonDeleteNote);
        addOrDelete = findViewById(R.id.editTextAddOrDelete);

        if (getIntent() != null && getIntent().getExtras() != null) {
            this.authToken = getIntent().getStringExtra(InClass07.authTokenKey);
        }

//        Toast.makeText(NotesPage.this, "token = " + authToken, Toast.LENGTH_SHORT).show();




        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://dev.sakibnm.space:3000/api/note/getall").header("x-access-token", authToken).build();

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
                            Gson gsonData = new Gson();
                            Notes notesObject = gsonData.fromJson(response.body().charStream(), Notes.class);
                            notes = notesObject.getNotes();
                            Log.d("InClass07", "onResponse: " + notesObject.toString());

                            recyclerView = findViewById(R.id.notesRecyclerView);
                            recyclerViewLayoutManager = new LinearLayoutManager(NotesPage.this);
                            recyclerView.setLayoutManager(recyclerViewLayoutManager);
                            notesAdapter = new NotesAdapter(notes);
                            recyclerView.setAdapter(notesAdapter);

                        }
                    });


                } else {
                    Log.d("InClass07", "response:" + response.body().string());
                }
            }
        });

        OkHttpClient client2 = new OkHttpClient();

        Request request2 = new Request.Builder()
                .url("http://dev.sakibnm.space:3000/api/auth/me").header("x-access-token", authToken).build();

        client.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("InClass07", "request failed for some reason");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            String string = null;
                            try {
                                assert response.body() != null;
                                string = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            JSONObject rootJson = null;
                            try {
                                assert string != null;
                                rootJson = new JSONObject(string);
                                userID = rootJson.getString("_id");
                                userName = rootJson.getString("name");
                                userEmail = rootJson.getString("email");
                                textViewDetails.setText("User ID: " + userID + "\n" +
                                        "Name: " + userName + "\n" +
                                        "Email: " + userEmail);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });


                } else {
                    Log.d("InClass07", "response:" + response.body().string());
                }
            }
        });

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String details = addOrDelete.getText().toString();

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("text", details)
                        .build();

                Request request = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/note/post?" +
                                "text=" + details).header("x-access-token", authToken).post(formBody).build();

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
                                    Toast.makeText(NotesPage.this, "Note Added", Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {
                            Log.d("InClass07", "response:" + response.body().string());
                        }
                    }
                });

            }

        });

        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String details = addOrDelete.getText().toString();

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("text", details)
                        .build();

                Request request = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/note/delete?" +
                                "id=" + details).header("x-access-token", authToken).post(formBody).build();

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
                                    Toast.makeText(NotesPage.this, "Note Deleted", Toast.LENGTH_SHORT).show();
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