package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SignUpScreen extends AppCompatActivity {
    private String username;
    private String password;
    private EditText usernameInput;
    private EditText passwordInput;
    private Button signUpButton;
    private DatabaseReference reference;
    private List<User> users = new ArrayList<User>();

    public void getUsers(){
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name = item.child("userName").getValue().toString();
                    String password = item.child("password").getValue().toString();
                    User temp = new User(name, password, null);
                    users.add(temp);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        signUpButton = (Button) findViewById(R.id.signupButton);
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsers();
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                User user = new User(username, password, null);
                Boolean isTaken = false;
                for(User u : users){
                    if(u.getUserName().equals(username)){
                        isTaken = true;
                    }
                }
                if(isTaken){
                    Toast.makeText(SignUpScreen.this,"Username already taken, choose a different one",Toast.LENGTH_SHORT).show();
                }else{
                    reference.push().setValue(user);
                    Toast.makeText(SignUpScreen.this,"New User Created Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}