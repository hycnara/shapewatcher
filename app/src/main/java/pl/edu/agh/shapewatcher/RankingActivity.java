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

public class RankingActivity extends AppCompatActivity{

    DatabaseReference databaseResults;

    ListView listViewRanking;
    static List<Result> resultList;

    public static List<Result> getResultList() {
        return resultList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        databaseResults = FirebaseDatabase.getInstance().getReference("results");
        listViewRanking = (ListView) findViewById(R.id.listViewRanking);
        resultList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseResults.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                resultList.clear();
                for(DataSnapshot resultSnapshot: dataSnapshot.getChildren()){
                    Result result = resultSnapshot.getValue(Result.class);
                    resultList.add(result);
                }

                Collections.sort(resultList);
                RankingList adapter = new RankingList(RankingActivity.this, resultList);
                listViewRanking.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
