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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button signup;
    private DatabaseReference reference;
    private EditText loginUserNameInput;
    private EditText loginPasswordInput;
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
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsers();
                Boolean checker = false;
                loginUserNameInput = (EditText) findViewById(R.id.loginUsernameInput);
                loginPasswordInput = (EditText) findViewById(R.id.loginPasswordInput);
                String username = loginUserNameInput.getText().toString();
                String password = loginPasswordInput.getText().toString();
                for(User u : users){
                    if(u.getUserName().equals(username) && u.getPassword().equals(password)){
                        checker = true;
                    }
                }
                if(checker){
                    openAccountScreen();
                }else{
                    Toast.makeText(MainActivity.this,"Username or Password is incorrect! Sign up if you don't have an account.", Toast.LENGTH_SHORT).show();
                }
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