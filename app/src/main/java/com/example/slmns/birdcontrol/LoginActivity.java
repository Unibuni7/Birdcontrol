package com.example.slmns.birdcontrol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{
    private Button btnsignin;
    private Button btnsignup;
    private EditText etEmail;
    private EditText etPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        btnsignin = (Button) findViewById(R.id.SignIn_btn);
        btnsignup = (Button) findViewById(R.id.SignUp_btn);
        etEmail = (EditText) findViewById(R.id.Email_et);
        etPassword = (EditText) findViewById(R.id.Pass_et);
        progressDialog = new ProgressDialog(this);

    }

    public void SignIn(View view) {
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        // checking if email and password are empty
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();

            //return will stop the function
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        // if validations are ok
        // we will first show a progressdialog

        progressDialog.setMessage("Signing in ....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    // Start the profile activity
                    finish();
                    //startActivity(new Intent(getApplicationContext(), FrontPageActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Access denied, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void SignUp(View view) {
        startActivity(new Intent(this,RegisterActivity.class));


    }


}
