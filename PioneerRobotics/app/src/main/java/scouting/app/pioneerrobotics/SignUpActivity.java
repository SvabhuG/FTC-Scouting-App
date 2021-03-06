package scouting.app.pioneerrobotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;


    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        editTextEmail = findViewById(R.id.team_name_signUp);
        editTextPassword = findViewById(R.id.password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sign_up_button).setOnClickListener(this);
    }

    private void registerUser(){
        String teamName = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (teamName.isEmpty()){
           editTextEmail.setError("Email is required");
           editTextEmail.requestFocus();
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

        mAuth.createUserWithEmailAndPassword(teamName.replaceAll(" ","") + "@gmail.com",password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration Successfull!", Toast.LENGTH_SHORT).show();
                    startGeneralTeamInfoActivity();
                } else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void startGeneralTeamInfoActivity(){
        startActivity(new Intent(this, GeneralTeamInfo.class));
    }

    public void onClick (View view) {
        switch (view.getId()){
            case R.id.sign_up_button:
                registerUser();
        }
    }
}
