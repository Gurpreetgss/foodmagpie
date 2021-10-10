package com.foodmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.foodmp.Fragment.HotelFragment;
import com.foodmp.Fragment.Individual_Organization_Activity;
import com.foodmp.Fragment.ResetPasswordFragment;
import com.foodmp.Fragment.SignupFragment;
import com.foodmp.controller.ApiUtils;
import com.foodmp.controller.UserServices;
import com.foodmp.model.LoginResponse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    UserServices userServices;

    EditText emailIdEditText;
    TextView errortxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailIdEditText = (EditText) findViewById(R.id.email_ed);
        EditText passwordEditText = (EditText) findViewById(R.id.password_ed);
        errortxt=(TextView) findViewById(R.id.errortextlogin);
        Button loginBtn=(Button) findViewById(R.id.btn_login);
        TextView signupText=(TextView) findViewById(R.id.signupText);
        TextView Resetpassword=(TextView) findViewById(R.id.Resetpassword);
        userServices = ApiUtils.getUserService();

        Resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frg=new ResetPasswordFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.replace(R.id.frag_frame,frg).commit();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaidId = emailIdEditText.getText().toString();
                String passwordText = passwordEditText.getText().toString();

                if (validateLogin(emaidId, passwordText)) {
                    AuthenticateUser(emaidId, passwordText);
                }
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupFragment.class));

            }
        });
    }


    public void AuthenticateUser(String email,String password) {
        try {
            Call<LoginResponse> call = userServices.loginUser(email,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {

                        if(response.body().getSuccess()){
                            new MaterialAlertDialogBuilder(LoginActivity.this).setMessage("Login Successfull")
                                    .setPositiveButton("ok",(dialog, which) -> {
                                        SharedPref shrd=new SharedPref(getApplication());
                                        //     shrd.setIslogin(true);
                                        shrd.setFirstname(response.body().getData().get(0).getFirstname());
                                        shrd.setUserId(response.body().getData().get(0).getUserid());

                                        String type=response.body().getData().get(0).getType();
                                        shrd.setType(type);
                                        if(response.body().getData().get(0).getType().equals("o")){

                                            startActivity(new Intent(LoginActivity.this, Individual_Organization_Activity.class));

                                        }else if(response.body().getData().get(0).getType().equals("u")){
                                          startActivity(new Intent(LoginActivity.this, User_View_Activity.class));

                                        }else{
                                          Fragment fragment=new HotelFragment();
                                          getSupportFragmentManager().beginTransaction().replace(R.id.frag_frame,fragment).commit();

                                        }


                                    }).show();

                        }else {

                            new MaterialAlertDialogBuilder(LoginActivity.this).setMessage(response.body().getMessage())
                                    .setPositiveButton("OK",(dialog, which) -> {
                                        ////
                                    }).show();
                        }
                    } else {

                        new MaterialAlertDialogBuilder(LoginActivity.this).setMessage("Error! Please try again!")
                                .setPositiveButton("OK",(dialog, which) -> {
                                    /////
                                }).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    new MaterialAlertDialogBuilder(LoginActivity.this).setMessage("Error! Please try again!")
                            .setPositiveButton("OK",(dialog, which) -> {
                                /////
                            }).show();
                }
            });
        }catch (Exception ex){

            new MaterialAlertDialogBuilder(LoginActivity.this).setMessage(ex.toString())
                    .setPositiveButton("OK",(dialog, which) -> {
                        /////
                    }).show();

        }
    }


    private boolean validateLogin(String email, String password) {
        if (email == null || email.trim().length() == 0 ) {

            errortxt.setText("Invalid email id");
            return false;
        }
        if (password == null || password.trim().length() == 0) {

            errortxt.setText("Password is required");
            return false;
        }
        return true;
    }
}