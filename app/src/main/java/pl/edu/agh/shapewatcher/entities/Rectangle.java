package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 02.11.2017.
 */

public class Rectangle extends Shape {
    double x,y;

    public Rectangle(double x, double y){
        super(ShapeType.RECTANGLE, getSurface(x,y));
        this.x = x;
        this.y = y;
    }

    private static double getSurface(double x, double y) {
        return x*y;
    }
}
