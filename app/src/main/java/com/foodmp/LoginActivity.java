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

import com.foodmp.Fragment.SignupFragment;

public class LoginActivity extends AppCompatActivity {
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
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaidId = emailIdEditText.getText().toString();
                String passwordText = passwordEditText.getText().toString();

                if (validateLogin(emaidId, passwordText)) {
                   // Authenticate(emaidId, passwordText);
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