package com.foodmp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodmp.LoginActivity;
import com.foodmp.MainActivity;
import com.foodmp.R;
import com.foodmp.controller.ApiUtils;
import com.foodmp.controller.UserServices;
import com.foodmp.model.Register;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupFragment extends AppCompatActivity {
    TextView errortxt;
    UserServices userServices;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);
        EditText firstNameEd=(EditText) findViewById(R.id.fname_ed);
        EditText ContactEd=(EditText) findViewById(R.id.contact_ed);
        EditText emailIdEd=(EditText) findViewById(R.id.emailid_ed);
        EditText passwordEd=(EditText) findViewById(R.id.createpassword_ed);
        RadioGroup type = (RadioGroup) findViewById(R.id.radioGroup_cat);

        EditText confirmPasswordEd=(EditText) findViewById(R.id.confirm_ed);
        RelativeLayout createAccount=(RelativeLayout) findViewById(R.id.rel_create);
        Button singupBtn=(Button) findViewById(R.id.problem);

        userServices = ApiUtils.getUserService();
        errortxt=(TextView) findViewById(R.id.errortext);
        singupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=firstNameEd.getText().toString();
                String contactnum=ContactEd.getText().toString();
                String emaidIdtext=emailIdEd.getText().toString();
                String passwordText=passwordEd.getText().toString();
                String confirmPass=confirmPasswordEd.getText().toString();
                int selectedId = type.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);

                if (validateLogin(first,emaidIdtext,contactnum,passwordText,confirmPass)) {
                    registerUser(first,emaidIdtext,contactnum,passwordText,radioButton.getText().toString());
                }
            }
        });
    }

    private void registerUser(String first, String emaidIdtext, String contactnum, String passwordText,String type)  {
        try {
            if(type.equals("Organizer")){
                type="o";
            }else if(type.equals("Hotel")){
                type="h";

            }else{
                type="u";
            }

            Call<Register> call = userServices.userRegister(first,emaidIdtext,contactnum,passwordText,type);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    if (response.isSuccessful()) {

                        if(response.body().getStatus()){
                            new MaterialAlertDialogBuilder(SignupFragment.this).setMessage("Account Created  Successfull")
                                    .setPositiveButton("ok",(dialog, which) -> {
                                        startActivity(new Intent(SignupFragment.this, LoginActivity.class));


                                    }).show();
                        }else{
                            new MaterialAlertDialogBuilder(SignupFragment.this).setMessage(response.body().getMessage())
                                    .setPositiveButton("OK",(dialog, which) -> {
                                        /////
                                    }).show();
                        }
                    } else {
                        //       Toast.makeText(getContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                        new MaterialAlertDialogBuilder(SignupFragment.this).setMessage(response.body().getMessage())
                                .setPositiveButton("OK",(dialog, which) -> {
                                    /////
                                }).show();
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    System.out.println("error");
                    new MaterialAlertDialogBuilder(SignupFragment.this).setMessage("Error! Please try again!")
                            .setPositiveButton("OK",(dialog, which) -> {
                                /////
                            }).show();
                }
            });
        }catch (Exception ex){
            new MaterialAlertDialogBuilder(SignupFragment.this).setMessage(ex.toString())
                    .setPositiveButton("OK",(dialog, which) -> {
                        /////
                    }).show();

        }
    }

    private boolean validateLogin(String first,String email,String contact, String password,String confirmPassword) {

        if (email == null || email.trim().length() == 0) {
            errortxt.setText("Email Id is required");
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            errortxt.setText("Password is required");
            return false;
        }
        if (first == null || first.trim().length() == 0) {
            errortxt.setText("First Name is required");
            return false;
        }

        if (contact == null || contact.trim().length() == 0) {
            errortxt.setText("Phone Number is required");
            return false;
        }
        if (confirmPassword == null || confirmPassword.trim().length() == 0) {
            errortxt.setText(" Confirm Password is required");
            return false;
        }
        if(!(password.equals(confirmPassword))){
            errortxt.setText("Both password should match");
            return false;
        }
        return true;
    }
}