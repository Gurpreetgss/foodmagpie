package com.foodmp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.foodmp.LoginActivity;
import com.foodmp.R;
import com.foodmp.SharedPref;

public class Individual_Organization_Activity extends AppCompatActivity {

    SharedPref shrdpreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual__organization_);
        shrdpreference=new SharedPref(getApplicationContext());
        Button signoutbtn=(Button) findViewById(R.id.signout_org);
        Button updateProfile=(Button) findViewById(R.id.updateProfile);
        boolean val=shrdpreference.isFirstTimeLaunchOrg();
        if(!val){
           // shrdpreference.setFirstTimeLaunchOrg(false);
            Fragment fragment=new update_org_Fragment();
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content,fragment).commit();
           // return;
        }

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shrdpreference.clearPreferences();
                startActivity(new Intent(Individual_Organization_Activity.this, LoginActivity.class));
            }
        });
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new update_org_Fragment();
                getSupportFragmentManager().beginTransaction().replace(android.R.id.content,fragment).commit();
            }
        });
    }
}