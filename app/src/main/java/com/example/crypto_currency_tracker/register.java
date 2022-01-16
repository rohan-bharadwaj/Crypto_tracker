package com.example.crypto_currency_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText con_pass,eemail,epass;
    Button eregi,lfaq2,back;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn = findViewById(R.id.SignIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, login.class));
            }
        });


        eemail = findViewById(R.id.email);
        epass = findViewById(R.id.password);
        con_pass = findViewById(R.id.con_pass);
        eregi = findViewById(R.id.btn_regi);
        lfaq2 = findViewById(R.id.log_faq2);
        back = findViewById(R.id.back);

        fAuth = FirebaseAuth.getInstance();


        lfaq2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)  {

                startActivity(new Intent(register.this, faq.class));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, after_gs.class));

            }
        });

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),register.class));

        }

        eregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eemail.getText().toString().trim();
                String password = epass.getText().toString().trim();
                String con_password = con_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    eemail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    epass.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(con_password)){
                    epass.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    epass.setError("Password must be greater than 6 characters");
                    return;
                }
                if(con_password.length() < 6){
                    epass.setError("Password must be greater than 6 characters");
                    return;
                }
                if(!password.equals(con_password)){
                    epass.setError("Passwords not matching");
                    con_pass.setError("Passwords not matching");
                    return;
                }

                // FIREBASE

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),login.class));


                    }
                    else{
                        Toast.makeText(register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });

    }
}