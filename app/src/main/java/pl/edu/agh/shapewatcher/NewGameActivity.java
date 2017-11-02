package pl.edu.agh.shapewatcher;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import pl.edu.agh.shapewatcher.entities.Shapes;

public class NewGameActivity extends AppCompatActivity  {

    private ProgressBar progressBar;
    private SeekBar seekBar;

    private TextView textViewScore;
    private TextView textViewRound;
    private TextView textViewBlue;
    private TextView textViewRed;

    private Shapes shapes = Shapes.getInstance();

    private CountDownTimer countDownTimer;

    private int score = 0;
    private int round = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        progressBar = (ProgressBar) findViewById(R.id.progressBarCountingDown);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textViewBlue = (TextView) findViewById(R.id.textViewBlue);
        textViewRed = (TextView) findViewById(R.id.textViewRed);

        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewRound = (TextView) findViewById(R.id.textViewRound);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewRed.setText(progress+getString(R.string.percent));
                textViewBlue.setText(100-progress+getString(R.string.percent));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        play();


    }

    private void play() {
        updateGameState();
        countDown();
    }

    private void countDown() {

        progressBar.setProgress(100);
        countDownTimer = new CountDownTimer(14000,140) {
            int i=100;
            @Override
            public void onTick(long millisUntilFinished) {
                i--;
                progressBar.setProgress(i);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                //start new comparison
            }
        };
        countDownTimer.start();
    }

    private void updateGameState(){
        textViewRound.setText("Round "+ round+ "/10");
        textViewScore.setText("Score: "+score);
    }
}
