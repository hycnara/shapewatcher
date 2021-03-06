package pl.edu.agh.shapewatcher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import pl.edu.agh.shapewatcher.entities.User;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private EditText editTextAge;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private Spinner spinnerEducation;
    private Button buttonRegister;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("users");
        progressDialog = new ProgressDialog(this);

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
        spinnerEducation = (Spinner) findViewById(R.id.spinnerEducation);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
        radioButtonFemale.setOnClickListener(this);
        radioButtonMale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
        if(v == radioButtonFemale){
            radioButtonMale.setChecked(false);
        }
        if(v == radioButtonMale){
            radioButtonFemale.setChecked(false);
        }
    }

    private void registerUser() {
        final String login = editTextLogin.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();
        int age = 0;
        if(!editTextAge.getText().toString().isEmpty())
            age = Integer.parseInt(editTextAge.getText().toString());
        String sex = radioButtonMale.isChecked() ? "Male" : "Female";
        String degree = spinnerEducation.getSelectedItem().toString();
        if(TextUtils.isEmpty(login)){
            Toast.makeText(this, getString(R.string.enter_login), Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password) || password.length() < 6){
            Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password2) || (!password2.equals(password))){
            Toast.makeText(this, getString(R.string.password_incorrect), Toast.LENGTH_SHORT).show();
            return;
        }
        if( age < 1 || age > 100){
            Toast.makeText(this, getString(R.string.valid_age), Toast.LENGTH_SHORT).show();
            return;
        }

        final User dbuser = new User(login,sex, degree,age);
        progressDialog.setMessage(getString(R.string.registering_user));
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(login).build();
                    user.updateProfile(profileUpdates);
                    databaseUser.child(login).setValue(dbuser);
                    progressDialog.dismiss();
                    finish();
                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                }else{
                    Toast.makeText(RegistrationActivity.this, getString(R.string.register_fail), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}
