package com.example.cslee.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText email,password;
    private Button signUpBtn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.r_email);
        password = (EditText) findViewById(R.id.r_password);
        signUpBtn = (Button) findViewById(R.id.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    protected void registerUser() {
        String emailText = email.getText().toString().trim();
        String passwordText  = password.getText().toString().trim();

        if(TextUtils.isEmpty(emailText)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(passwordText)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty, dispay progress dialog
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);

                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
