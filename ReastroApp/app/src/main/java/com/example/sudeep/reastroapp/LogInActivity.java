package com.example.sudeep.reastroapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    TextView textViewSignup,textViewReset,textViewAdmin,textViewUser;
    Button buttonLogin;
    private String parentDBName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewAdmin=findViewById(R.id.textViewAdmin);
        textViewUser=findViewById(R.id.textViewUser);
        progressBar = findViewById(R.id.progressbar);
        textViewSignup=findViewById(R.id.textViewSignup);
        buttonLogin=findViewById(R.id.buttonLogin);
        textViewReset=findViewById(R.id.textViewAdmin);
        textViewSignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        textViewReset.setOnClickListener(this);

    }
    private void UserForget(){
        {
            String userEmail = textViewReset.getText().toString();
            if (TextUtils.isEmpty(userEmail)) {
                textViewReset.setError("Please enter an email address.");
                textViewReset.requestFocus();

            } else {
                mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Intent intent = new Intent();
                            Toast.makeText(LogInActivity.this, "Please Check Your Email", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseNetworkException) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                    }
                    else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                    {
                        Toast.makeText(getApplicationContext(), "Username and Password do not match", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "User Not Registered", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSignup:
                finish();
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.buttonLogin:
                userLogin();
                break;

            case R.id.textViewAdmin:
                 buttonLogin.setText("Login Admin");
                 textViewAdmin.setVisibility(View.INVISIBLE);
                 textViewUser.setVisibility(View.VISIBLE);
                 parentDBName="Admin";
                 break;

            case R.id.textViewUser:
                buttonLogin.setText("Login");
                textViewAdmin.setVisibility(View.VISIBLE);
                textViewUser.setVisibility(View.INVISIBLE);
                parentDBName="Users";
                break;

        }
    }
}
