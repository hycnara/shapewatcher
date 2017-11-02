package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 02.11.2017.
 */

public class Square extends Shape {
    private double a;

    public Square(int a){
        super(ShapeType.SQUARE, getSurface(a));
        this.a = a;
    }

    private static double getSurface(double a) {
        return a*a;
    }
}
