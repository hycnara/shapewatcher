package pl.edu.agh.shapewatcher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
         //   startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmailLogIn);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogIn);
        buttonLogIn = (Button) findViewById(R.id.buttonLogIn);

        progressDialog = new ProgressDialog(this);

        buttonLogIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogIn){
            loginUser();
        }
    }

    private void loginUser() {
        // log user and set flag isLoggedSuccesfully to TRUE
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in");
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   Toast.makeText(LoginActivity.this, "Logged in succesfully.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Logging unsuccesfull. Please use valid email & password.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }
}
