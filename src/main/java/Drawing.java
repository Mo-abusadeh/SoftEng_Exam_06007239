import Shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class Drawing extends Canvas {
    private ArrayList<Shape> shapes;

    public Drawing(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void paint(Graphics g){
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}
