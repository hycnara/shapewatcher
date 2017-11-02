package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 02.11.2017.
 */

public class Circle extends Shape {
    public double radius;

    public Circle(double rad){
        super(ShapeType.CIRCLE, getSurface(rad));
        radius = rad;
    }

    private static double getSurface(double rad) {
        return Math.PI * rad* rad;
    }
}
