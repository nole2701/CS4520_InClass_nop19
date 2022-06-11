package com.example.in_class_demo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login_InClass08#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login_InClass08 extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth mAuth;
    private ILoginFragmentAction mListener;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button registerButton;
    private Button loginButton;
    private String userEmail;
    private String userPassword;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public login_InClass08() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login_InClass08.
     */
    // TODO: Rename and change types and number of parameters
    public static login_InClass08 newInstance(String param1, String param2) {
        login_InClass08 fragment = new login_InClass08();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof  ILoginFragmentAction) {
            this.mListener = (ILoginFragmentAction) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement PopulateMainFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_init_in_class08, container, false);
        editTextEmail = view.findViewById(R.id.editTextEmailLoginInClass08);
        editTextPassword = view.findViewById(R.id.editTextPasswordLoginInClass08);
        loginButton = view.findViewById(R.id.logInButtonInClass08);
        registerButton = view.findViewById(R.id.registerButtonInClass08);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("InClass08", "clicked register");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerInClass08, new register_InClass08(), "register_fragment")
                        .addToBackStack(null)
                        .commit();

            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login__in_class08, container, false);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logInButtonInClass08) {
            Log.d("InClass08", "onClick: Login");
            userEmail = editTextEmail.getText().toString().trim();
            userPassword = editTextPassword.getText().toString().trim();
            if (userEmail.equals("")) {
                editTextEmail.setError("Must input an email");
            }
            if (userPassword.equals("")) {
                editTextPassword.setError("Must input a password");
            }
            if (!userEmail.equals("") && !userPassword.equals("")) {
                mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Login Failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    mListener.populateMainFragment(mAuth.getCurrentUser());
                                }
                            }
                        });
            }

        } else if (view.getId() == R.id.registerButtonInClass08) {
            Log.d("InClass08", "onClick: Register");
            mListener.populateRegisterFragment();
        }
    }

    public interface ILoginFragmentAction {
        void populateMainFragment(FirebaseUser mUser);
        void populateRegisterFragment();
    }
}