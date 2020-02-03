package com.example.pioneerrobotics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    TextInputEditText editTextTeamName, editTextPassword;
    SignUpActivity activity = new SignUpActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mAuth = FirebaseAuth.getInstance();
        editTextTeamName = findViewById(R.id.team_name);
        editTextPassword = findViewById(R.id.password_signIn);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        findViewById(R.id.sign_up_textview).setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);
    }

    private void userLogin(){


        String teamName = editTextTeamName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (teamName.isEmpty()){
            editTextTeamName.setError("Email is required");
            editTextTeamName.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Minimum length for password is 6");
            editTextPassword.requestFocus();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this,
                R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(teamName.replaceAll(" ","")+"@gmail.com", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startGeneralActivity();
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startGeneralActivity(){
        startActivity(new Intent(this, GeneralTeamInfo.class));
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.sign_up_textview:
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.login_button:
                userLogin();
                break;
        }
    }

}
