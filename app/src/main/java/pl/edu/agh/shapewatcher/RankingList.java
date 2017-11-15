package pl.edu.agh.shapewatcher;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.agh.shapewatcher.entities.Result;

/**
 * Created by acer on 15.11.2017.
 */

public class RankingList extends ArrayAdapter<Result> {

    private Activity context;
    private List<Result> userList;

    public RankingList(Activity context, List<Result> userList){
        super(context, R.layout.ranking_list, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.ranking_list, null, true);
        TextView textView = (TextView) listViewItem.findViewById(R.id.textViewRankingUser);

        Result result = userList.get(position);
        textView.setText(result.getUserScore()+ " "+ result.getUserLogin());

        return listViewItem;
    }
}
