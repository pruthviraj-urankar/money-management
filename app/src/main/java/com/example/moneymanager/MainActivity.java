package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.util.Log;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  {

    private static int SPLASH_SCREEN_TIME_OUT=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.
        Log.d("Main","In main activity");
        setContentView(R.layout.activity_main);
        //this will bind your MainActivity.class file with activity_main.

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent is used to switch from one activity to another.

                FirebaseAuth mAuth ;
                mAuth=FirebaseAuth.getInstance();
                FirebaseUser currentUser=mAuth.getCurrentUser();
                if(currentUser==null)
                {
                    Intent i=new Intent(MainActivity.this,
                            Login.class);
                    startActivity(i);
                    //invoke the SecondActivity.

                    finish();//the current activity will get finished.
                }
                else
                {
                    /*Intent i=new Intent(MainActivity.this,homeActivity.class);
                    startActivity(i);
                    finish();
                     */

                    Log.e("Running","Already logged in");
                    Intent i=new Intent(MainActivity.this,home.class);
                    startActivity(i);
                    finish();
                }


            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
