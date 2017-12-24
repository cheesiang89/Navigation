package com.example.cslee.navigation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity; //make sures that it is backward compatible
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.io.Console;


public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private EditText emailText, passwordText;
    private Button loginBtn, registerBtn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        emailText = (EditText) findViewById(R.id.email);
        emailText.setText("lee@example.com");
        passwordText = (EditText) findViewById(R.id.password);
        passwordText.setText("password");
        registerBtn = (Button) findViewById(R.id.register);
        loginBtn = (Button) findViewById(R.id.login);
        LoginButton FBloginBtn = (LoginButton) findViewById(R.id.fb_login);
        FBloginBtn.setReadPermissions("email");
        getLoginDetails(FBloginBtn);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance(); //gets the firebase auth object


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                loginRequest();
            }
        });



    }

    //method to login user using email and password
    protected void loginRequest() {
        String email = emailText.getText().toString().trim();
        String password  = passwordText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please la, email not entered leh",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter your password lah!",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Hold on...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successful
                        if (task.isSuccessful()) {
                            //start the profile activity
                           finish();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            //display some message here
                            Toast.makeText(LoginActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    //Initialize the facebook sdk and callback manager will handle login responses
    protected void facebookSDKInitialize() {
        callbackManager = CallbackManager.Factory.create();
    }

    //Register a callback function with LoginButton to respond to the login result
    protected void getLoginDetails(LoginButton login_button){
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult login_result) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                AccessToken AT = AccessToken.getCurrentAccessToken();
                Log.d("access token", AT.toString());
            }
            @Override
            public void onCancel() {
                // code for cancellation
            }
            @Override
            public void onError(FacebookException exception) {
                //  code to handle error
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume(); //logs install and app activate app event
        AppEventsLogger.activateApp(getApplication());
    }

    @Override
    protected void onPause() {
        super.onPause(); //logs app deactivate app event
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data",data.toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }}


