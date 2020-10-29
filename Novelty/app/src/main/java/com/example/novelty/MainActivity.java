package com.example.novelty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    //send user to Login activity once logged out.
    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent (getApplicationContext(), Login.class));
        finish(); //
    }
}