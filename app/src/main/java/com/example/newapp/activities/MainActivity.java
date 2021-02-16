package com.example.newapp.activities;

/**
 * Creer par Koffitse Aboudou .
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {


    private EditText mEmail;
    private  EditText mPass;
    private Button btnLogin;
    private TextView mForgetPass;
    private TextView mSigninHere;
    // PasswortEingabe Versteken
    //private Button btnLogin;
    private CheckBox mPasswordDisplayCheck;

    private ProgressDialog mDialog;

    //http://www.tutorialsface.com/2015/10/android-validating-email-edittext-sample-example-methods-tutorial/
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Deaktivieren der Bildschirmaufzeichnung
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        mAuth= FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        mDialog=new ProgressDialog(this);
        LoginDetails();

        // initViews();
    }

    public void LoginDetails() {

        mEmail=(EditText)findViewById(R.id.email_log);
        mPass=(EditText)findViewById(R.id.password_login);
        btnLogin=findViewById(R.id.btn_login);
        mForgetPass=findViewById(R.id.forget_paasword);
        mSigninHere=findViewById(R.id.sign_eup);

        mPass.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        mPasswordDisplayCheck = (CheckBox) findViewById(R.id.password_display_check);


        btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email=mEmail.getText().toString().trim();
        String pass=mPass.getText().toString().trim();
        //if(TextUtils.isEmpty(email)){
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(),"Enter email address",Toast.LENGTH_SHORT).show();

                // mEmail.setError("Email Required...");
                 return;
            }

            if (TextUtils.isEmpty(pass)){

                Toast.makeText(getApplicationContext(),"Password Required",Toast.LENGTH_SHORT).show();
            //mPass.setError("Password Required..");
            return;
        }



            else {

                if (mEmail.getText().toString().trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }

            }



     //deuxième possibilité

      // mEmail.setError("Email Required...");
      // return;
     // }

      //  if(TextUtils.isEmpty(pass)) {

       //     mPass.setError("Pass Required...");
        //    return;
       // }

      //  mDialog.setMessage("Processing..");
       // mDialog.show();

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if (task.isSuccessful()){

                    mDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    Toast.makeText(getApplicationContext(),"Login Successful..",Toast.LENGTH_SHORT).show();
                }else {
                    //ActivityCompat.finishAffinity(MainActivity.this);

                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Login Failed..",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
        });



        mPasswordDisplayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    mPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    mPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });












       // Passwortversteken Fonktion
        mPasswordDisplayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    mPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    mPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


//Registration

mSigninHere.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));

    }
});

//Reset password activity.

   mForgetPass.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           startActivity(new Intent(getApplicationContext(),ResetActivity.class));

       }
   });

    }
// CONTINUTE DU MOT DE PASS CACHE
    public static class AsteriskPasswordTransformationMethod implements TransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        @Override
        public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {

        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;

            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }

            public char charAt(int index) {
                return '*';
            }

            public int length() {
                return mSource.length(); // Return default
            }

            public CharSequence subSequence(int start,int end) {
                return mSource.subSequence(start,end); // Return default
            }
        }}
    };

