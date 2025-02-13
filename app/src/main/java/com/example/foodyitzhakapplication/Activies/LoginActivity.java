package com.example.foodyitzhakapplication.Activies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodyitzhakapplication.R;
import com.example.foodyitzhakapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        seyVariable();
        binding.tvSignUp.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));


    }

    private void seyVariable() {
        binding.btnLogin2.setOnClickListener(v -> {
            String email = binding.edtEmail.getText().toString();
            String password = binding.edtPass.getText().toString();
            if(!email.isEmpty() && !password.isEmpty()){
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, task -> {
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                    }

                });
            }else{
                Toast.makeText(LoginActivity.this, "please fill username and password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}