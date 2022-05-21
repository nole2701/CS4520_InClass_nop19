package com.example.in_class_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link submitActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class submitActivityFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";
    private static final String ARG_EMAIL = "email";
    private static final String ARG_MOODSTRING = "moodString";
    private static final String ARG_IUSESTRING = "iUseString";
    private static final String ARG_MOODFILEID = "moodFileId";
    private static final String ARG_AVATARFILEID = "avatarFileID";


    private String name;
    private String email;
    private String moodString;
    private String iUseString;
    private int moodFileID;
    private int avatarFileID;

    public submitActivityFragment() {
        // Required empty public constructor
    }


    public static submitActivityFragment newInstance(String name, String email, String moodString,
                                                     String iUseString, int moodFileID, int avatarFileID) {
        submitActivityFragment fragment = new submitActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_EMAIL, email);
        args.putString(ARG_MOODSTRING, moodString);
        args.putString(ARG_IUSESTRING, iUseString);
        args.putInt(ARG_MOODFILEID, moodFileID);
        args.putInt(ARG_AVATARFILEID, avatarFileID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            email = getArguments().getString(ARG_EMAIL);
            moodString = getArguments().getString(ARG_MOODSTRING);
            iUseString = getArguments().getString(ARG_IUSESTRING);
            moodFileID = getArguments().getInt(ARG_MOODSTRING);
            avatarFileID = getArguments().getInt(ARG_AVATARFILEID);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit_activity, container, false);

        TextView nameResult;
        TextView emailResult;
        TextView iUseResult;
        TextView moodResult;
        ImageView moodImage;
        ImageView avatarImage;

        nameResult = view.findViewById(R.id.nameResult);
        emailResult = view.findViewById(R.id.emailResult);
        iUseResult = view.findViewById(R.id.iUseView);
        moodResult = view.findViewById(R.id.iAmMood);
        moodImage = view.findViewById(R.id.moodImageFinal);
        avatarImage = view.findViewById(R.id.avatarFinal);

        nameResult.setText("Name: " + name);
        emailResult.setText("Email: " + email);
        iUseResult.setText("I use: " + iUseString + "!");
        moodResult.setText("I am: " + moodString + "!");
        moodImage.setImageResource(moodFileID);
        avatarImage.setImageResource(avatarFileID);

        return view;
    }

}