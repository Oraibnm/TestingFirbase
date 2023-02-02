package com.example.testingfirbase;



import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class Register extends AppCompatActivity {

    EditText email , password ;
    Button register ;

    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email = findViewById(R.id.email_Register);
        password = findViewById(R.id.password_Register);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username  = email.getText().toString();
                String pass = password.getText().toString() ;
           Toast.makeText(Register.this,"Your account is created" + username + pass, Toast.LENGTH_SHORT).show();
          RegisterFirebase(username,pass);

            }
        });
    }


    public void RegisterFirebase (String userEmail , String userPassword) {
        Toast.makeText(Register.this,"Hello register"+ userEmail + userPassword , Toast.LENGTH_SHORT).show();
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task  ) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this,"yesssssss", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Register.this,"Nooooooo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}