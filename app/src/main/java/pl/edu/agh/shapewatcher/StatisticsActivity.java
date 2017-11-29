package pl.edu.agh.shapewatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.agh.shapewatcher.entities.Result;
import pl.edu.agh.shapewatcher.entities.Round;

public class StatisticsActivity extends AppCompatActivity{

    DatabaseReference databaseRounds;

    ListView listViewStatistics;
    List<Round> roundList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        databaseRounds = FirebaseDatabase.getInstance().getReference("rounds");
        listViewStatistics = (ListView) findViewById(R.id.listViewStatistics);
        roundList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseRounds.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                roundList.clear();
                for(DataSnapshot resultSnapshot: dataSnapshot.getChildren()){
                    Round round = resultSnapshot.getValue(Round.class);
                    roundList.add(round);
                }

                StatisticsList adapter = new StatisticsList(StatisticsActivity.this, roundList);
                listViewStatistics.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}