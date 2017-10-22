package pl.edu.agh.shapewatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    private TextView textViewHello;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        String user = firebaseAuth.getCurrentUser().getDisplayName();
        textViewHello = (TextView) findViewById(R.id.textViewHello);
        textViewHello.setText("Hello, "+ user);
    }
}
