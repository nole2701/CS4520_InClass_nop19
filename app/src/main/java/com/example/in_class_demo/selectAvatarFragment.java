package com.example.in_class_demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link selectAvatarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class selectAvatarFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public selectAvatarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment selectAvatarFragment.
     */
    public static selectAvatarFragment newInstance(String param1, String param2) {
        selectAvatarFragment fragment = new selectAvatarFragment();
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
        getActivity().setTitle("Select Avatar");
    }

    IFromFragmentToActivity sendData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_avatar, container, false);
        view.setBackgroundColor(Color.WHITE);

        final int[] selectedAvatar = new int[1];

        ImageView avatar1 = view.findViewById(R.id.avatar1);
        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_f_1;
                int avatarID = selectedAvatar[0];
                sendData.fromFragment(avatarID);
            }
        });

        ImageView avatar2 = view.findViewById(R.id.avatar2);
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_f_2;
                int avatarID = selectedAvatar[0];
                sendData.fromFragment(avatarID);
            }
        });
//
        ImageView avatar3 = view.findViewById(R.id.avatar3);
        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_f_3;
                int avatarID = selectedAvatar[0];
                sendData.fromFragment(avatarID);
            }
        });

        ImageView avatar4 = view.findViewById(R.id.avatar4);
        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_m_1;
                int avatarID = selectedAvatar[0];
                sendData.fromFragment(avatarID);
            }
        });

        ImageView avatar5 = view.findViewById(R.id.avatar5);
        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_m_2;
                int avatarID = selectedAvatar[0];
                sendData.fromFragment(avatarID);
            }
        });

        ImageView avatar6 = view.findViewById(R.id.avatar6);
        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity population...
                selectedAvatar[0] = R.mipmap.avatar_m_3;
                int avatarID = selectedAvatar[0];
                sendData.fromFragment(avatarID);
            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IFromFragmentToActivity) {
            sendData = (IFromFragmentToActivity) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IFromFragmentToActivity");
        }
    }

    public interface IFromFragmentToActivity {
        void fromFragment(int avatarID);
    }


}