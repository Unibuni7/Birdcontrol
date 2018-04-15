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

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnSignIn;
    private EditText etEmail;
    private EditText etPass;
    private EditText etpass2;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        btnRegister = (Button) findViewById(R.id.btn_SignUp);
        btnSignIn = (Button) findViewById(R.id.btn_SignIn);
        etEmail = (EditText) findViewById(R.id.et_Email);
        etPass = (EditText) findViewById(R.id.et_Pass);
        etpass2 = (EditText) findViewById(R.id.et_Pass2);

    }

    public void SignUp(View view) {

        String Email = etEmail.getText().toString().trim();
        String Password = etPass.getText().toString().trim();
        String Password2 = etpass2.getText().toString().trim();

        if (TextUtils.isEmpty(Email)){
            //email is empty
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            // return will stop the funtion
            return;
        }
        if (TextUtils.isEmpty(Password)){
            //password is empty
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password2)){
            // Confirm password is empty
            Toast.makeText(this, "Please RE-enter Password again", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Password.equals(Password2)) {
            Toast.makeText(this, "The password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        if (Password.equals(Password2)) {
            firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {

                        Toast.makeText(RegisterActivity.this,"You are registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), FrontActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this,"Try again, the registration failed",Toast.LENGTH_SHORT).show();


                    }

                }
            });
        }



    }

    public void SignIn(View view) {

        startActivity(new Intent(this,LoginActivity.class));
    }
}
