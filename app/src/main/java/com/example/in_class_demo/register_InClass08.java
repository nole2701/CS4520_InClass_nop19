package com.example.in_class_demo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register_InClass08#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register_InClass08 extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Button registerButton;
    private EditText editTextFirstName, editTextLastName, editTextDisplayName,
            editTextEmail, editTextPassword, editTextRepeatPassword;
    private String firstName, lastName, displayName, email, password, repeatPassword;
    private IRegisterFragmentAction mListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public register_InClass08() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register_InClass08.
     */
    // TODO: Rename and change types and number of parameters
    public static register_InClass08 newInstance(String param1, String param2) {
        register_InClass08 fragment = new register_InClass08();
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
        if (context instanceof IRegisterFragmentAction) {
            this.mListener = (IRegisterFragmentAction) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement RegisterRequest");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register__in_class08, container, false);
        editTextFirstName = view.findViewById(R.id.editTextRegisterFirstName);
        editTextLastName = view.findViewById(R.id.editTextRegisterLastName);
        editTextDisplayName = view.findViewById(R.id.editTextRegisterFragmentDisplayName);
        editTextEmail = view.findViewById(R.id.editTextEmailLoginInClass08);
        editTextPassword = view.findViewById(R.id.editTextRegisterPassword1);
        editTextRepeatPassword = view.findViewById(R.id.editTextRegisterPassword2);
        registerButton = view.findViewById(R.id.buttonRegisterInClass08Register);
        registerButton.setOnClickListener(this);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register__in_class08, container, false);
    }

    @Override
    public void onClick(View view) {
        this.firstName = editTextFirstName.getText().toString().trim();
        this.lastName = editTextLastName.getText().toString().trim();
        this.displayName = editTextDisplayName.getText().toString().trim();
        this.email = editTextEmail.getText().toString().trim();
        this.password = editTextPassword.getText().toString().trim();
        this.repeatPassword = editTextRepeatPassword.getText().toString().trim();

        if (view.getId() == R.id.buttonRegisterInClass08Register) {
            if (!firstName.equals("") && !lastName.equals("") && !displayName.equals("")
                && !email.equals("") && !password.equals("") && !repeatPassword.equals("")) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mUser = task.getResult().getUser();

                                    // Add name to FirebaseUser...
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(displayName)
                                            .build();

                                    mUser.updateProfile(profileChangeRequest)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        mListener.registerDone(mUser);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
            }
        }



    }
    public interface IRegisterFragmentAction {
        void registerDone(FirebaseUser mUser);
    }
}