package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 02.11.2017.
 */

public class Triangle extends Shape {
    double a,h;

    public Triangle(double a, double h){
        super(ShapeType.TRIANGLE, getSurface(a,h));
        this.a = a;
        this.h = h;
    }

    private static double getSurface(double a, double h) {
        return 1/2*a*h;
    }
}
