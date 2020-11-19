package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button signup;
    private EditText loginUserNameInput;
    private EditText loginPasswordInput;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checker = false;
                loginUserNameInput = (EditText) findViewById(R.id.loginUsernameInput);
                loginPasswordInput = (EditText) findViewById(R.id.loginPasswordInput);
                String username = loginUserNameInput.getText().toString();
                String password = loginPasswordInput.getText().toString();
                DocumentReference tempref = db.collection("Users").document(username);
                tempref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            if(documentSnapshot.get("password").equals(password)){
                                openAccountScreen();
                            }else{
                                Toast.makeText(MainActivity.this,"Username or password is incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Username or password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
        signup = (Button) findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupScreen();
            }
        });
    }
    public void openAccountScreen() {
        Intent intent = new Intent(this, AccountScreen.class);
        startActivity(intent);
    }
    public void openSignupScreen() {
        Intent intent = new Intent(this, SignUpScreen.class);
        startActivity(intent);
    }
}