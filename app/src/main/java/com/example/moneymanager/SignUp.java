package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private AutoCompleteTextView EmailTextView;
    private EditText usernameTextView;
    private EditText passwordTextView;
    private Button signUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        final int[] result = new int[1];
        InitaliseUi();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result[0] =signup();
            }
        });
        progressBar.setVisibility(View.GONE);
        Intent intent=new Intent();
        if(result[0]==1) {
            setResult(result[0]);
            finish();
        }


    }


    public void InitaliseUi()
    {
        usernameTextView=findViewById(R.id.username);
        EmailTextView=findViewById(R.id.email);
        passwordTextView=findViewById(R.id.password);
        signUp=findViewById(R.id.email_sign_up_button);
        progressBar=findViewById(R.id.login_progress);

    }
    public int signup()
    {
        String username=usernameTextView.getText().toString();
        String email=EmailTextView.getText().toString();
        String password=passwordTextView.getText().toString();

        if(username!=null && email != null && password != null)
        {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d("CreateUser","createUserWithEmail:Success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Log.w("CreateUser", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            return 1;
        }
        else
            return 0;
    }
}
