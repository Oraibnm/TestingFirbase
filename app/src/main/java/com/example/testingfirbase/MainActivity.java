package com.example.testingfirbase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText name  ;
    Button Login_btn ;
    TextView textView;

    //object from firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //object from database reference class , must add child user or recipe
    DatabaseReference reference = database.getReference().child("user");

    //this reference for get data from database and I don't need to child
    DatabaseReference referenceToGet = database.getReference();



    EditText email , password ;
    Button login , register , forgetPasswordBtn , phoneNumber;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.eamil);
        password = findViewById(R.id.password);
        login = findViewById(R.id.Loginbtn);
        register = findViewById(R.id.Register);
        forgetPasswordBtn = findViewById(R.id.forgetPassword);
        phoneNumber = findViewById(R.id.phoneNumber);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = email.getText().toString();
                String pass = password.getText().toString();
                LoginFirebase(username, pass);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });

        forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(i);
            }
        });

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        name = findViewById(R.id.username);
//        Login_btn = findViewById(R.id.btn);
//        textView = findViewById(R.id.textView);

        //call or get data from database
//        referenceToGet.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//              String nameFromDB = (String) snapshot.child("user").child("name").getValue();
//              textView.setText(nameFromDB);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        //send data to database
//        Login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userName = name.getText().toString();
//
//                reference.child("phone").setValue(userName);
//
//            }
//        });



    }

    public void  LoginFirebase (String email , String password){

        Toast.makeText(MainActivity.this,"Hello Log in "+ email + password , Toast.LENGTH_SHORT).show();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task  ) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"yesssssss", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(MainActivity.this,"Nooooooo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if ( user != null){
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
            finish();

        }
    }
}