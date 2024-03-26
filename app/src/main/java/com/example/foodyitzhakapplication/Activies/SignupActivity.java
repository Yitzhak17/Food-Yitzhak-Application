package com.example.foodyitzhakapplication.Activies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodyitzhakapplication.Models.UserModel;
import com.example.foodyitzhakapplication.R;
import com.example.foodyitzhakapplication.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.Objects;

public class SignupActivity extends BaseActivity {
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        binding.tvLogin.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));

    }

    private void setVariable() {
        binding.btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPass.getText().toString();
                String firstName = binding.edtFirstName.getText().toString();
                String familyName = binding.edtFamilyName.getText().toString();
                String phoneNumber = binding.edtPhone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupActivity.this, "email is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(firstName)){
                    Toast.makeText(SignupActivity.this, "First Name is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(familyName)){
                    Toast.makeText(SignupActivity.this, "Family Name is empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (phoneNumber.length()!=10){
                    Toast.makeText(SignupActivity.this, "your phone number must be 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length()<6){
                    Toast.makeText(SignupActivity.this, "your password must be 6 characters least.", Toast.LENGTH_SHORT).show();
                    return;
                }




                // create user

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
                            Log.i(TAG, "onComplete: ");
                            UserModel user = new UserModel(email,firstName,familyName,phoneNumber,password);
                            String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                            database.getReference().child("Users").child(id).setValue(user);

                            Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();;

                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            intent.putExtra("firstName", firstName);
                            intent.putExtra("familyName", familyName);
                            startActivity(intent);

                        }else{
                            Log.i(TAG, "failure: "+task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();;
                        }

                        }
                });
            }
        });
    }
}