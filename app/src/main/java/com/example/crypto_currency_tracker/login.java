package com.example.crypto_currency_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText memail,mpass;
    Button login,lfaq,back1;
    FirebaseAuth fAuth;
    TextView fpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail = findViewById(R.id.lemail);
        mpass = findViewById(R.id.lpass);
        login = findViewById(R.id.login);
        lfaq = findViewById(R.id.log_faq);
        fAuth = FirebaseAuth.getInstance();
        fpass = findViewById(R.id.fpass);
        back1 = findViewById(R.id.back1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpass.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    mpass.setError("Password must be greater than 6 characters");
                    return;
                }
                // Authenticate user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),tracker.class));
                            finish();
                        }
                        else{
                            Toast.makeText(login.this, "Login Unsucessfull !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        TextView btn = findViewById(R.id.SignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));

            }
        });

        lfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, faq.class));

            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, after_gs.class));
                finish();
            }
        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset password");
                passwordResetDialog.setMessage("Enter your email address to receive reset link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String mail = resetMail.getText().toString();
                                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(login.this, "Reset link sent to your email.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(login.this, "Error! Reset link not sent. " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();

            }
        });
    }
}