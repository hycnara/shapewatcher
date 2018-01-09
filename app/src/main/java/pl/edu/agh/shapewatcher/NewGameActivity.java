package pl.edu.agh.shapewatcher;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.edu.agh.shapewatcher.entities.Result;
import pl.edu.agh.shapewatcher.entities.Round;

public class NewGameActivity extends AppCompatActivity  implements View.OnClickListener{

    private ProgressBar progressBar;
    private SeekBar seekBar;
    private ImageView imageViewBlue;
    private ImageView imageViewRed;
    private Button buttonEndRound;

    private TextView textViewScore;
    private TextView textViewRound;
    private TextView textViewBlue;
    private TextView textViewRed;

    private CountDownTimer countDownTimer;
    private Bitmap bitmapBlue;
    private Bitmap bitmapRed;
    private Canvas canvasBlue;
    private Canvas canvasRed;
    private Paint paintBlue;
    private Paint paintRed;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseResults;
    private DatabaseReference  databaseRounds;

    private List<Integer> results = new ArrayList<Integer>();
    private int score = 0;
    private int round = 1;
    private int roundScore = 0;
    private double surfaceBlue = 0;
    private double surfaceRed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseResults = FirebaseDatabase.getInstance().getReference("results");
        databaseRounds = FirebaseDatabase.getInstance().getReference("rounds");

        progressBar = (ProgressBar) findViewById(R.id.progressBarCountingDown);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        imageViewBlue = (ImageView) findViewById(R.id.imageViewBlue);
        imageViewRed = (ImageView) findViewById(R.id.imageViewRed);
        buttonEndRound = (Button) findViewById(R.id.buttonEndRound);
        textViewBlue = (TextView) findViewById(R.id.textViewBlue);
        textViewRed = (TextView) findViewById(R.id.textViewRed);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewRound = (TextView) findViewById(R.id.textViewRound);

        buttonEndRound.setOnClickListener(this);
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

        setCanvas();
        play();
    }

    private void setCanvas() {
        bitmapBlue = Bitmap.createBitmap(250, 210, Bitmap.Config.ARGB_8888 );
        canvasBlue = new Canvas(bitmapBlue);
        paintBlue = new Paint();
        paintBlue.setStyle(Paint.Style.FILL);
        paintBlue.setColor(getResources().getColor(R.color.blue));
        paintBlue.setAntiAlias(true);

        bitmapRed = Bitmap.createBitmap(250, 210, Bitmap.Config.ARGB_8888 );
        canvasRed = new Canvas(bitmapRed);
        paintRed = new Paint();
        paintRed.setStyle(Paint.Style.FILL);
        paintRed.setColor(getResources().getColor(R.color.red));
        paintRed.setAntiAlias(true);
    }

    private void play() {
        if(round == 11){
            --round;
            updateGameState();
            endGame();
            return;
        }
        updateGameState();
        seekBar.setEnabled(true);
        repaint();
        drawShapes();
        countDown();
    }

    private void repaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.butter));
        paint.setAntiAlias(true);
        canvasBlue.drawRect(0, 0, 250, 210, paint);
        canvasRed.drawRect(0,0,250,210,paint);
        imageViewBlue.setImageBitmap(bitmapBlue);
        imageViewRed.setImageBitmap(bitmapRed);
    }

    private void drawShapes() {
        switch (round){
            case 1:
                surfaceBlue = drawCircle(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawSquare(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 2:
                surfaceBlue = drawRectangle(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawCircle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 3:
                surfaceBlue = drawSquare(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawRectangle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 4:
                surfaceBlue = drawTriangle(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawCircle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 5:
                surfaceBlue = drawSquare(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawTriangle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 6:
                surfaceBlue = drawTriangle(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawRectangle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 7:
                surfaceBlue = drawStar(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawCircle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 8:
                surfaceBlue = drawSquare(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawStar(canvasRed, imageViewRed,paintRed, bitmapRed);
                break;
            case 9:
                surfaceBlue = drawStar(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawRectangle(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
            case 10:
                surfaceBlue = drawTriangle(canvasBlue, imageViewBlue, paintBlue, bitmapBlue);
                surfaceRed = drawStar(canvasRed, imageViewRed, paintRed, bitmapRed);
                break;
        }
    }

    private void endGame() {
      //  saveResultToDataBase(this.score);
        showFinalScore(this.score);
        if(SettingsActivity.isSoundOn())
            MediaPlayer.create(this, R.raw.congratulations).start();
    }

    private void showFinalScore(int sc) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("You've earned +"+score+" points!");
        builder1.setMessage("Congratulations!");
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                (dialog, id) -> {
                    saveResultToDataBase(sc);
                    dialog.cancel();
                    finish();
                    startActivity(new Intent(NewGameActivity.this, RankingActivity.class));
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void saveResultToDataBase(int score) {
        String userLogin = firebaseAuth.getCurrentUser().getDisplayName();
        final Result finalResult = new Result(userLogin, score);
        databaseResults.child(userLogin+score).setValue(finalResult);
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
                countPoints();
            }
        };
        countDownTimer.start();
    }

    private void countPoints() {
        roundScore = 0;
        String color = "blue";

        double allSurf = surfaceBlue + surfaceRed;
        int bluePercentage = (int) Math.round(surfaceBlue*100/allSurf);
        int redPercentage = (int) Math.round(surfaceRed*100/allSurf);

        int userBluePercentage = Integer.parseInt(textViewBlue.getText().toString().replace("%",""));
        int userRedPercentage = Integer.parseInt(textViewRed.getText().toString().replace("%",""));

        if(bluePercentage > redPercentage && userBluePercentage > userRedPercentage){
            roundScore+=5;
        }
        else if(bluePercentage == redPercentage && userBluePercentage == userRedPercentage){
            roundScore+=5;
        }
        else if(redPercentage > bluePercentage && userRedPercentage > userBluePercentage){
            roundScore+=5;
        }

        if(redPercentage > bluePercentage)
            color = "red";

        int diff = Math.abs(bluePercentage - userBluePercentage);

        if(diff == 0) {
            roundScore += 5;
            if(SettingsActivity.isSoundOn())
                MediaPlayer.create(this, R.raw.win).start();
        }
        else if(diff >=1 && diff <=3)
            roundScore+=4;
        else if(diff >=4 && diff <=10)
            roundScore+=3;
        else if(diff >=11 && diff <=20)
            roundScore +=2;
        else if(diff >=21 && diff <= 30)
            roundScore += 1;

        if(seekBar.isEnabled())
            score += roundScore;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Score +"+roundScore+" points");
        builder1.setMessage("Blue shape: "+ bluePercentage+"%\n"
                            +"Red shape: "+ redPercentage+"%");
        builder1.setCancelable(true);
        String finalColor = color;
        builder1.setNeutralButton(android.R.string.ok,
                (dialog, id) -> {
                    saveRoundToDatabase(roundScore, round, finalColor);
                    dialog.cancel();
                    round++;
                    play();
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        seekBar.setEnabled(false);
    }

    private void saveRoundToDatabase(int roundScore, int round, String color){
        String userLogin = firebaseAuth.getCurrentUser().getDisplayName();
        final Round round1 = new Round(userLogin,round,roundScore,color);
        databaseRounds.child(databaseRounds.push().getKey()).setValue(round1);
    }

    private void updateGameState(){
        textViewRound.setText("Round "+ round+ "/10");
        textViewScore.setText("Score: "+score);
    }

    private double drawCircle(Canvas canvas, ImageView imageView, Paint paint, Bitmap bitmap){
        int i = new Random().nextInt(10)+1;
        int radius = 50 + i*5;

        canvas.drawCircle(125,105,radius,paint);
        imageView.setImageBitmap(bitmap);

        return Math.PI*radius*radius;
    }

    private double drawSquare(Canvas canvas, ImageView imageView, Paint paint, Bitmap bitmap){
        int i = new Random().nextInt(10)+1;
        int a = 50 + i*15;

        canvas.drawRect(25+15*(10-i), 5+15*(10-i), 25+a+15*(10-i), 5+a+15*(10-i), paint);
        imageView.setImageBitmap(bitmap);

        return a*a;
    }

    private double drawRectangle(Canvas canvas, ImageView imageView, Paint paint, Bitmap bitmap){
        int i = new Random().nextInt(10)+1;
        int j = new Random().nextInt(10)+1;
        int a = 50 + i*15;
        int b = 30 + j*20;

        canvas.drawRect(10+20*(10-j), 5+15*(10-i), 10+b+20*(10-j), 5+a+15*(10-i), paint);
        imageView.setImageBitmap(bitmap);

        return a*b;
    }

    private double drawTriangle(Canvas canvas, ImageView imageView, Paint paint, Bitmap bitmap){
        int aX = new Random().nextInt(100);
        int aY = new Random().nextInt(140);
        int bX = new Random().nextInt(80)+170;
        int bY = new Random().nextInt(140);
        int cX = new Random().nextInt(100)+80;
        int cY = new Random().nextInt(70)+140;

        paint.setStrokeWidth(4);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(bX, bY);
        path.lineTo(cX, cY);
        path.lineTo(aX, aY);
        path.close();

        canvas.drawPath(path, paint);
        imageView.setImageBitmap(bitmap);

        return Math.abs(((bX-aX)*(cY-aY))-((bY-aY)*(cX-aX)))/2;
    }

    private double drawStar(Canvas canvas, ImageView imageView, Paint paint, Bitmap bitmap){

        Path path = new Path();

        int x = new Random().nextInt(15)+5;
        float aX = new Random().nextInt(90);
        float aY = new Random().nextInt(60)+60;
        float bX = aX + 8 * x;
        float bY = aY;
        float cX = aX + 1.4f * x;
        float cY = aY + 4 * x;
        float dX = aX + 4 * x;
        float dY = aY - 3 * x;
        float eX = bX - 1.4f * x;
        float eY = cY;

        path.moveTo(aX, aY);
        path.lineTo(bX, bY);
        path.lineTo(cX, cY);
        path.lineTo(dX, dY);
        path.lineTo(eX, eY);
        path.close();

        canvas.drawPath(path, paint);
        imageView.setImageBitmap(bitmap);

        return  (10*Math.tan(Math.PI/10))/(3- Math.tan(Math.PI/10)*Math.tan(Math.PI/10))*17*x*x;
    }

    @Override
    public void onClick(View v) {
        if(v == buttonEndRound){
            endRound();
        }
    }

    private void endRound() {
        countDownTimer.cancel();
        progressBar.setProgress(0);
        countPoints();
    }
}
