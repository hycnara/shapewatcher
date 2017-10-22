package pl.edu.agh.shapewatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewHello;
    private Button buttonNewGame;
    private Button buttonStatistics;
    private Button buttonRanking;
    private Button buttonSettings;
    private Button buttonLogOut;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        String user = firebaseAuth.getCurrentUser().getDisplayName();
        textViewHello = (TextView) findViewById(R.id.textViewHello);
        textViewHello.setText("Hello, "+ user+ "!");

        buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        buttonStatistics = (Button) findViewById(R.id.buttonStatistics);
        buttonRanking = (Button) findViewById(R.id.buttonRanking);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);

        buttonNewGame.setOnClickListener(this);
        buttonStatistics.setOnClickListener(this);
        buttonRanking.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonNewGame){

        }
        if(v == buttonStatistics){

        }
        if(v == buttonRanking){

        }
        if(v == buttonSettings){

        }
        if(v == buttonLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MenuActivity.this, WelcomeActivity.class));
        }
    }
}
