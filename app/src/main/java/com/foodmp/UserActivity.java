package com.foodmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;

import com.foodmp.Fragment.HistoryFragment;
import com.foodmp.Fragment.OrganizationsFragment;
import com.foodmp.Fragment.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class UserActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        bottomNavigationView = findViewById(R.id.userbottomNavigationView);


        bottomNavigationView.setSelectedItemId(R.id.organization);
        OrganizationsFragment firstFragment = new OrganizationsFragment();
        HistoryFragment secondFragment = new HistoryFragment();
        UserProfileFragment thirdFragment = new UserProfileFragment();
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhiteDark));

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.organization:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, firstFragment).commit();
                       break;

                    case R.id.history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).commit();

                        break;
                    case R.id.userprofile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
                       break;
                }
            }
        });

    }



}