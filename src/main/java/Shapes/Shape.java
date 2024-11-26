package Shapes;

import java.awt.*;

// Implementation of Shape as an Abstract Class
public abstract class Shape {

    protected Point position;
    protected Color color;

    public Shape(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    public abstract void draw(Graphics g);
}


//Implementation of Shape as an Interface
//public interface Shape {
//
//    void draw(Graphics g);
//}