package pl.edu.agh.shapewatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.achartengine.GraphicalView;

import java.util.HashMap;

import pl.edu.agh.shapewatcher.utilities.GraphValues;
import pl.edu.agh.shapewatcher.utilities.PieGraph;

public class AnalysisActivity extends AppCompatActivity {
    HashMap<Integer, GraphValues> roundResults;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null){
            roundResults = (HashMap<Integer, GraphValues>)bundle.getSerializable("roundResults");
        }

        PieGraph yummyPie = new PieGraph();  

        GraphicalView graphicalView1 = yummyPie.getGraphicalView(this, roundResults.get(1).blueValue,roundResults.get(1).redValue,"CIRCLE", "SQUARE");
        LinearLayout pieGraph1 = (LinearLayout) findViewById(R.id.pieGraph1);
        pieGraph1.addView(graphicalView1);

        GraphicalView graphicalView2 = yummyPie.getGraphicalView(this, roundResults.get(2).blueValue,roundResults.get(2).redValue,"RECTANGLE", "CIRCLE");
        LinearLayout pieGraph2 = (LinearLayout) findViewById(R.id.pieGraph2);
        pieGraph2.addView(graphicalView2);

        GraphicalView graphicalView3 = yummyPie.getGraphicalView(this, roundResults.get(3).blueValue,roundResults.get(3).redValue,"SQUARE", "RECTANGLE");
        LinearLayout pieGraph3 = (LinearLayout) findViewById(R.id.pieGraph3);
        pieGraph3.addView(graphicalView3);

        GraphicalView graphicalView4 = yummyPie.getGraphicalView(this, roundResults.get(4).blueValue,roundResults.get(4).redValue,"TRIANGLE", "CIRCLE");
        LinearLayout pieGraph4 = (LinearLayout) findViewById(R.id.pieGraph4);
        pieGraph4.addView(graphicalView4);

        GraphicalView graphicalView5 = yummyPie.getGraphicalView(this, roundResults.get(5).blueValue,roundResults.get(5).redValue,"SQUARE", "TRIANGLE");
        LinearLayout pieGraph5 = (LinearLayout) findViewById(R.id.pieGraph5);
        pieGraph5.addView(graphicalView5);

        GraphicalView graphicalView6 = yummyPie.getGraphicalView(this, roundResults.get(6).blueValue,roundResults.get(6).redValue,"TRIANGLE", "RECTANGLE");
        LinearLayout pieGraph6 = (LinearLayout) findViewById(R.id.pieGraph6);
        pieGraph6.addView(graphicalView6);

        GraphicalView graphicalView7 = yummyPie.getGraphicalView(this, roundResults.get(7).blueValue,roundResults.get(7).redValue,"STAR", "CIRCLE");
        LinearLayout pieGraph7 = (LinearLayout) findViewById(R.id.pieGraph7);
        pieGraph7.addView(graphicalView7);

        GraphicalView graphicalView8 = yummyPie.getGraphicalView(this, roundResults.get(8).blueValue,roundResults.get(8).redValue,"SQUARE", "STAR");
        LinearLayout pieGraph8 = (LinearLayout) findViewById(R.id.pieGraph8);
        pieGraph8.addView(graphicalView8);

        GraphicalView graphicalView9 = yummyPie.getGraphicalView(this, roundResults.get(9).blueValue,roundResults.get(9).redValue,"STAR", "RECTANGLE");
        LinearLayout pieGraph9 = (LinearLayout) findViewById(R.id.pieGraph9);
        pieGraph9.addView(graphicalView9);

        GraphicalView graphicalView10 = yummyPie.getGraphicalView(this, roundResults.get(10).blueValue,roundResults.get(10).redValue,"TRIANGLE", "STAR");
        LinearLayout pieGraph10 = (LinearLayout) findViewById(R.id.pieGraph10);
        pieGraph10.addView(graphicalView10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

    }
}
