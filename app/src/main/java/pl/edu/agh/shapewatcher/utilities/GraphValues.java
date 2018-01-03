package pl.edu.agh.shapewatcher.utilities;

import java.io.Serializable;

/**
 * Created by acer on 02.01.2018.
 */

public class GraphValues implements Serializable {
    public int blueValue;
    public int redValue;

    public GraphValues(){
        blueValue = 1;
        redValue = 1;
    }
}
