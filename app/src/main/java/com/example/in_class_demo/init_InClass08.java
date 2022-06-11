package com.example.in_class_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link init_InClass08#newInstance} factory method to
 * create an instance of this fragment.
 */
public class init_InClass08 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button loginButton;
    private Button registerButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public init_InClass08() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InitInClass08.
     */
    // TODO: Rename and change types and number of parameters
    public static init_InClass08 newInstance(String param1, String param2) {
        init_InClass08 fragment = new init_InClass08();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("InClass08", "view created");
        View view = inflater.inflate(R.layout.fragment_init_in_class08, container, false);
        registerButton = view.findViewById(R.id.buttonGoRegister);
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
        loginButton = view.findViewById(R.id.buttonGoLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("InClass08", "clicked login");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerInClass08, new login_InClass08(), "login_fragment")
                        .addToBackStack(null)
                        .commit();

            }
        });






        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_init_in_class08, container, false);
    }
}