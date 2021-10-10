package com.foodmp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.foodmp.R;
import com.foodmp.controller.UserServices;

public class update_org_Fragment extends Fragment {
    TextView errortxt;
    UserServices userServices;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_update_org_, container, false);
        EditText orgName=(EditText) view.findViewById(R.id.orgname_ed);
        EditText orgaddress=(EditText) view.findViewById(R.id.orgaddress_ed);
        EditText orgdesc=(EditText) view.findViewById(R.id.orgdesc_ed);
        EditText orgphone=(EditText) view.findViewById(R.id.orgphone_ed);

        return view;
    }
}