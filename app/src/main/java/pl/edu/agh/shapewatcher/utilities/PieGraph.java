package pl.edu.agh.shapewatcher.utilities;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import pl.edu.agh.shapewatcher.R;

/**
 * Created by acer on 01.01.2018.
 */

public class PieGraph {
    public GraphicalView getGraphicalView(Context context, int blueSlide, int redSlide, String blueFigure, String redFigure){
        CategorySeries series = new CategorySeries("");
        int [] portions = {blueSlide, redSlide};
        String[] seriesNames = new String[]{blueFigure,redFigure};

        int numSlide = 2;
        for(int i=0; i<numSlide; i++){
            series.add(seriesNames[i], portions[i]);
        }
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        SimpleSeriesRenderer simpleSeriesRenderer = null;

        int[] colors = {ContextCompat.getColor(context, R.color.blue), ContextCompat.getColor(context, R.color.red)};

        for(int i=0; i<numSlide; i++){
            simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(colors[i]);
            defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);
        }

        return ChartFactory.getPieChartView(context, series,defaultRenderer);
    }
}
