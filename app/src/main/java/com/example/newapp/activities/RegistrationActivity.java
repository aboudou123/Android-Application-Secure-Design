package com.example.newapp.activities;

/**
 * Creer par Koffitse Aboudou .
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.newapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPass;
    private Button btnReg;
    private TextView mSignin;

    private ProgressDialog mDialog;

    //Firebase#
    private FirebaseAuth mAuth;
    //Email validation
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        registration();


       // initViews();
    }

    public void registration() {


        mEmail = (EditText) findViewById(R.id.email_reg);
        mPass = (EditText) findViewById(R.id.password_reg);
        btnReg = (Button) findViewById(R.id.btn_reg);
        mSignin = (TextView) findViewById(R.id.signin_here);



        // mEmail.setText("aboudou");

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String pass = mPass.getText().toString().trim();

               // if(mEmail.getText().toString().isEmpty()) {

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();

                   // mEmail.setError("Email Required...");
                   // return;
                }
                else {

                    if (mEmail.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }

                }

// lassen
             //   if (TextUtils.isEmpty(pass)) {

                //    mPass.setError("Pass Required...");
                    // return;
               // }
              //  mDialog.setMessage("Processing..");
             //   mDialog.show();


          //

                //  String pass = null;
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mDialog.dismiss();

                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(),"Registration Complete",Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(getApplicationContext(),HomeActivity .class));
                                } else {
                                    mDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Registration Field",Toast.LENGTH_LONG).show();
                                   //startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                }



                             // Firerbase user

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    // Name, email address, and profile photo Url
                                    String name = user.getDisplayName();
                                    String email = user.getEmail();
                                    Uri photoUrl = user.getPhotoUrl();

                                    // Check if user's email is verified
                                    boolean emailVerified = user.isEmailVerified();

                                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                                    // authenticate with your backend server, if you have one. Use
                                    // FirebaseUser.getIdToken() instead.
                                    String uid = user.getUid();
                                }


                                //End  Firerbase user

                            }
                        });


            }
        });

        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

    }
}


