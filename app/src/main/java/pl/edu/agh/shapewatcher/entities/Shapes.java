package pl.edu.agh.shapewatcher.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 01.11.2017.
 */

public class Shapes {
    public  List<Shape> shapeList;
    private static Shapes shapes;

    private Shapes(){
        shapeList = new ArrayList<>();
        addRandomShapes();
    }

    private void addRandomShapes() {
        shapeList.add(new Circle(1));
        shapeList.add(new Circle(2));
        shapeList.add(new Circle(3));
        shapeList.add(new Circle(4));
        shapeList.add(new Circle(5));
        shapeList.add(new Circle(6));
        shapeList.add(new Circle(7));
        shapeList.add(new Circle(8));
        shapeList.add(new Circle(9));
        shapeList.add(new Circle(10));

        shapeList.add(new Rectangle(2,4));
        shapeList.add(new Rectangle(1,4));
        shapeList.add(new Rectangle(3,9));
        shapeList.add(new Rectangle(1,7));
        shapeList.add(new Rectangle(7,4));
        shapeList.add(new Rectangle(8,4));
        shapeList.add(new Rectangle(3,2));
        shapeList.add(new Rectangle(5,6));
        shapeList.add(new Rectangle(8,6));
        shapeList.add(new Rectangle(2,5));

        shapeList.add(new Square(1));
        shapeList.add(new Square(2));
        shapeList.add(new Square(3));
        shapeList.add(new Square(4));
        shapeList.add(new Square(5));
        shapeList.add(new Square(6));
        shapeList.add(new Square(7));
        shapeList.add(new Square(8));
        shapeList.add(new Square(9));
        shapeList.add(new Square(10));

        shapeList.add(new Triangle(2,4));
        shapeList.add(new Triangle(1,4));
        shapeList.add(new Triangle(3,9));
        shapeList.add(new Triangle(1,7));
        shapeList.add(new Triangle(7,4));
        shapeList.add(new Triangle(8,4));
        shapeList.add(new Triangle(3,2));
        shapeList.add(new Triangle(5,6));
        shapeList.add(new Triangle(8,6));
        shapeList.add(new Triangle(2,5));
    }

    public static Shapes getInstance(){
        if(shapes == null){
            shapes = new Shapes();
        }
        return shapes;
    }
}
