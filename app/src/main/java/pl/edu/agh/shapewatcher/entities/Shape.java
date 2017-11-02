package pl.edu.agh.shapewatcher.entities;

/**
 * Created by acer on 01.11.2017.
 */

public class Shape {
    private static int ID  = 0;
    private int id;
    private double surface ;
    private ShapeType shapeType;

    public Shape(){this.id = ++ID;}
    public Shape(ShapeType shapeType, double surface) {
        this.id = ++ID;
        this.shapeType = shapeType;
        this.surface = surface;
    }

    public  int getId() {
        return id;
    }

    public double getSurface() {
        return surface;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }
}
