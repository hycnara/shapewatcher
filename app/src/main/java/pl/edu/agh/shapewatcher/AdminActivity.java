package pl.edu.agh.shapewatcher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.shapewatcher.utilities.GraphValues;


public class AdminActivity extends AppCompatActivity implements View.OnClickListener{
    CheckBox checkBoxMale, checkBoxFemale;
    CheckBox checkBoxPrimary, checkBoxSecondary, checkBoxHigh, checkBoxBachelor,
            checkBoxMaster, checkBoxDoctorate;
    EditText editTextAgeDown, editTextAgeUp;

    Button buttonShowResults;

    private boolean isMaleChecked, isFemaleChecked, isPrimaryEducationChecked, isSecondaryEducationChecked, isHighSchoolChecked,
            isBachelorChecked, isMasterChecked, isDoctorateChecked;
    private int finalAgeUp, finalAgeDown;

    private DatabaseReference databaseRefUsers;

    private List<String> userList = new ArrayList<>();
    private HashMap<Integer, GraphValues> roundsResults = new HashMap<>() ;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        databaseRefUsers = FirebaseDatabase.getInstance().getReference();

        checkBoxMale = (CheckBox) findViewById(R.id.checkBoxMale);
        checkBoxFemale = (CheckBox) findViewById(R.id.checkBoxFemale);
        checkBoxPrimary = (CheckBox) findViewById(R.id.checkBoxPrimary);
        checkBoxSecondary = (CheckBox) findViewById(R.id.checkBoxSecondary);
        checkBoxHigh = (CheckBox) findViewById(R.id.checkBoxHigh);
        checkBoxBachelor = (CheckBox) findViewById(R.id.checkBoxBachelor);
        checkBoxMaster = (CheckBox) findViewById(R.id.checkBoxMaster);
        checkBoxDoctorate = (CheckBox) findViewById(R.id.checkBoxDoctorate);

        editTextAgeDown = (EditText) findViewById(R.id.editTextAgeDown);
        editTextAgeUp = (EditText) findViewById(R.id.editTextAgeUp);

        buttonShowResults = (Button) findViewById(R.id.buttonShowResults);
        buttonShowResults.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

        for(int i=1; i<=10; i++){
            roundsResults.put(i, new GraphValues());
        }

    }


    @Override
    public void onClick(View v) {
        if (v == buttonShowResults){
            progressDialog.setMessage("Processing data");
            progressDialog.show();
            analyze();
            getRoundResults();
        }
    }

    private void getRoundResults() {
        databaseRefUsers.child("rounds").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                roundsResults.clear();
                for(int i=1; i<=10; i++){
                    roundsResults.put(i, new GraphValues());
                }
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) dataSnapshot.getValue()).entrySet()){
                    Map singleRound = (Map) entry.getValue();
                    checkRound(singleRound);
                }
                Intent intent = new Intent(AdminActivity.this, AnalysisActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("roundResults",roundsResults);
                intent.putExtras(extras);
                progressDialog.dismiss();
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void checkRound(Map singleRound) {
        String userLogin = (String) singleRound.get("userLogin");
        long round = (Long) singleRound.get("round");
        long score = (Long) singleRound.get("score");
        String biggerFigure = (String) singleRound.get("biggerFigureColor");
        if(userList.contains(userLogin)){
            if(score < 8){
                if(biggerFigure.equals("blue")){
                    roundsResults.get((int)round).blueValue+=1;
                }else{
                    roundsResults.get((int)round).redValue+=1;
                }
            }
        }
    }

    private void analyze() {
        isMaleChecked = checkBoxMale.isChecked();
        isFemaleChecked = checkBoxFemale.isChecked();
        int ageDown, ageUp;
        try {
            ageDown = Integer.parseInt(editTextAgeDown.getText().toString().trim());
        }catch (Exception e){
            ageDown = 0;
        }
        try{
            ageUp = Integer.parseInt(editTextAgeUp.getText().toString().trim());
        }catch (Exception e){
            ageUp = 100;
        }
        isPrimaryEducationChecked = checkBoxPrimary.isChecked();
        isSecondaryEducationChecked = checkBoxSecondary.isChecked();
        isHighSchoolChecked = checkBoxHigh.isChecked();
        isBachelorChecked = checkBoxBachelor.isChecked();
        isMasterChecked = checkBoxMaster.isChecked();
        isDoctorateChecked = checkBoxDoctorate.isChecked();

        finalAgeDown = ageDown;
        finalAgeUp = ageUp;


        databaseRefUsers.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) dataSnapshot.getValue()).entrySet()){
                    Map singleUser = (Map) entry.getValue();
                    if(isOkUser(singleUser)){
                        userList.add((String) singleUser.get("userLogin"));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private boolean isOkUser(Map singleUser) {
        String userSex = (String)singleUser.get("userSex");
        String userDegree = (String) singleUser.get("userDegree");
        long userAge1 = (Long) singleUser.get("userAge");
        int userAge = (int) userAge1;
        if((userSex.equals("Male") && isMaleChecked) || (userSex.equals("Female") && isFemaleChecked)){
            if(userAge >= finalAgeDown && userAge<= finalAgeUp){
                if((userDegree.equals("Primary education") && isPrimaryEducationChecked)
                        || (userDegree.equals("Secondary education") && isSecondaryEducationChecked)
                        || (userDegree.equals("High school") && isHighSchoolChecked)
                        || (userDegree.equals("Bachelor's degree") && isBachelorChecked)
                        || (userDegree.equals("Master's degree") && isMasterChecked)
                        || (userDegree.equals("Doctorate") && isDoctorateChecked)){
                        return  true;
                }
            }
        }
        return false;
    }


}
