package pl.edu.agh.shapewatcher;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private static boolean soundOn = true;

    Button buttonHowToPlay;
    Button buttonSoundEffects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonHowToPlay = (Button) findViewById(R.id.buttonHowToPlay);
        buttonSoundEffects = (Button) findViewById(R.id.buttonSound);

        soundOn = getIntent().getBooleanExtra("sound", true);
        if (soundOn) {
            buttonSoundEffects.setText(getString(R.string.sound_effects_on));
        } else {
            buttonSoundEffects.setText(getString(R.string.sound_effects_off));
        }

        buttonHowToPlay.setOnClickListener(this);
        buttonSoundEffects.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonHowToPlay){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("How to play");
            builder1.setMessage("Use the bar on the right side to predict which shape is greater.");
            builder1.setCancelable(true);
            builder1.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                     ;
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        if(v == buttonSoundEffects) {
            if (soundOn) {
                buttonSoundEffects.setText(getString(R.string.sound_effects_off));
                soundOn = false;
            } else {
                soundOn = true;
                buttonSoundEffects.setText(getString(R.string.sound_effects_on));
            }
        }
    }

    public static  boolean isSoundOn(){
        return soundOn;
    }
}
