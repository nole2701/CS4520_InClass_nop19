package com.example.in_class_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class InClass08 extends AppCompatActivity
        implements login_InClass08.ILoginFragmentAction,
                    register_InClass08.IRegisterFragmentAction {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class08);
        setTitle("InClass08");
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        populateScreen();
    }

    private void populateScreen() {
        if(currentUser != null) {
            //TODO
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerInClass08, new init_InClass08(), "init_fragment")
                    .commit();
        }
    }

    @Override
    public void populateMainFragment(FirebaseUser mUser) {
        this.currentUser = mUser;
        populateScreen();
    }



    @Override
    public void populateRegisterFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragmentContainerInClass08, new register_InClass08(), "register_fragment")
//                .addToBackStack(null)
                .commit();
    }

    @Override
    public void registerDone(FirebaseUser mUser) {
        this.currentUser = mUser;
        populateScreen();
    }
}