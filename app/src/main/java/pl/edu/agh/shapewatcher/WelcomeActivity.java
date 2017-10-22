package pl.edu.agh.shapewatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private Button buttonLogin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(WelcomeActivity.this, MenuActivity.class));
        }

        buttonLogin = (Button) findViewById(R.id.buttonLoginWelcome);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
        }
        if(v == buttonLogin){
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        }

    }
}
