package pl.edu.agh.shapewatcher;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            startActivity(new Intent(MenuActivity.this, NewGameActivity.class));
        }
        if(v == buttonStatistics){
            startActivity(new Intent(MenuActivity.this, StatisticsActivity.class));
        }
        if(v == buttonRanking){
            startActivity(new Intent(MenuActivity.this, RankingActivity.class));
        }
        if(v == buttonSettings){
            Intent i = new Intent(MenuActivity.this, SettingsActivity.class);
            i.putExtra("sound",SettingsActivity.isSoundOn());
            startActivity(i);
        }
        if(v == buttonLogOut){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Log out")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            firebaseAuth.signOut();
                            finish();
                            startActivity(new Intent(MenuActivity.this, WelcomeActivity.class));
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
