package com.foodmp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodmp.LoginActivity;
import com.foodmp.R;
import com.foodmp.controller.ApiUtils;
import com.foodmp.controller.UserServices;
import com.foodmp.model.Register;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResetPasswordFragment extends Fragment {

    TextView errortxt;
    UserServices userServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_reset_password, container, false);

        EditText resetEmailId=(EditText) view.findViewById(R.id.remail_ed);
        EditText resetpassword=(EditText) view.findViewById(R.id.r_password_ed);
        EditText resetConfirm=(EditText) view.findViewById(R.id.r_confirm_ed);
        Button rel_reset=(Button) view.findViewById(R.id.reset_btn);
        errortxt=(TextView) view.findViewById(R.id.errortext);
        userServices = ApiUtils.getUserService();

        rel_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailed=resetEmailId.getText().toString();
                String passworded=resetpassword.getText().toString();
                String cpassworded=resetConfirm.getText().toString();
                if(validate(emailed,passworded,cpassworded)){
                    ResetPassword(emailed,passworded);
                }
            }
        });
        return view;
    }

    private void ResetPassword(String emailed, String passworded) {
        try {
            Call<Register> call = userServices.resetPassword(emailed,passworded);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    if (response.isSuccessful()) {

                        if(response.body().getStatus()){
                            new MaterialAlertDialogBuilder(getActivity()).setMessage("Password Reset Successfully")
                                    .setPositiveButton("OK",(dialog, which) -> {
                                        startActivity(new Intent(getActivity(), LoginActivity.class));

                                    }).show();

                        }else {
                            new MaterialAlertDialogBuilder(getActivity()).setMessage(response.body().getMessage())
                                    .setPositiveButton("OK",(dialog, which) -> {

                                    }).show();
                        }
                    } else {
                        new MaterialAlertDialogBuilder(getActivity()).setMessage("Error! Please try again!")
                                .setPositiveButton("OK",(dialog, which) -> {

                                }).show();

                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    System.out.println("error");
                }
            });
        }catch (Exception ex){
            System.out.println(ex.toString());

        }
    }

    private boolean validate(String email, String password,String confirmPassword) {

        if (email == null || email.trim().length() == 0) {
            errortxt.setText("Email Id is required");
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            errortxt.setText("Password is required");
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