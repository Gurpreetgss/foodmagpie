package com.foodmp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.foodmp.LoginActivity;
import com.foodmp.R;
import com.foodmp.SharedPref;


public class UserProfileFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_profile2, container, false);
        Button logoutBtn=(Button) view.findViewById(R.id.logoutbtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref shrd=new SharedPref(getActivity());
                shrd.clearPreferences();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }
}