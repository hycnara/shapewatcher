package pl.edu.agh.shapewatcher;

import android.app.Activity;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.shapewatcher.entities.Result;
import pl.edu.agh.shapewatcher.entities.Round;

/**
 * Created by acer on 29.11.2017.
 */

public class StatisticsList extends ArrayAdapter<Round> {

    private Activity context;
    private List<Round> roundList;
    private Map<Integer,String> stats = new HashMap<Integer,String>();

    public StatisticsList(Activity context, List<Round> userList){
        super(context, R.layout.statistics_list, userList);
        this.context = context;
        this.roundList = userList;

        makeStatistics();
    }

    private void makeStatistics() {
        String user = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        List<Integer> round1 = new ArrayList<>();
        List<Integer> round2 = new ArrayList<>();
        List<Integer> round3 = new ArrayList<>();
        List<Integer> round4 = new ArrayList<>();
        List<Integer> round5 = new ArrayList<>();
        List<Integer> round6 = new ArrayList<>();
        List<Integer> round7 = new ArrayList<>();
        List<Integer> round8 = new ArrayList<>();
        List<Integer> round9 = new ArrayList<>();
        List<Integer> round10 = new ArrayList<>();

        for (Round round : roundList) {
            if(round.getUserLogin().equals(user)){
                switch (round.getRound()){
                    case 1:
                        round1.add(round.getScore());
                        break;
                    case 2:
                        round2.add(round.getScore());
                        break;
                    case 3:
                        round3.add(round.getScore());
                        break;
                    case 4:
                        round4.add(round.getScore());
                        break;
                    case 5:
                        round5.add(round.getScore());
                        break;
                    case 6:
                        round6.add(round.getScore());
                        break;
                    case 7:
                        round7.add(round.getScore());
                        break;
                    case 8:
                        round8.add(round.getScore());
                        break;
                    case 9:
                        round9.add(round.getScore());
                        break;
                    case 10:
                        round10.add(round.getScore());
                        break;
                }
            }

        }
        stats.put(0,"Shapes:    Percentage:");
        stats.put(1,"circle/square: "+ getAverage(round1));
        stats.put(2,"rectangle/circle: "+ getAverage(round2));
        stats.put(3,"square/rectangle: "+ getAverage(round3));
        stats.put(4,"triangle/circle: "+ getAverage(round4));
        stats.put(5,"square/triangle: "+ getAverage(round5));
        stats.put(6,"triangle/rectangle: "+ getAverage(round6));
        stats.put(7,"star/square: "+ getAverage(round7));
        stats.put(8,"square/star: "+ getAverage(round8));
        stats.put(9,"star/rectangle: "+ getAverage(round9));
        stats.put(10,"triangle/star: "+ getAverage(round10));

    }

    private String getAverage(List<Integer> round) {
        int d = 0;
        for (int i: round)
            d+=i;
        int average = d/round.size();
        switch (average){
            case 0:
                return "  0%";
            case 1:
                return "  <20%";
            case 2:
                return "  >20%";
            case 3:
                return "  >30%";
            case 4:
                return "  >45%";
            case 5:
                return "  >60%";
            case 6:
                return "  >70%";
            case 7:
                return "  >80%";
            case 8:
                return "  >90%";
            case 9:
                return "  >97%";
            case 10:
                return "   100%";

        }

        return "";
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.statistics_list, null, true);
        TextView textView = (TextView) listViewItem.findViewById(R.id.textViewStatistics);
        textView.setText(stats.get(position));

        return listViewItem;
    }

    @Override
    public int getCount() {
        return stats.size();
    }
}
