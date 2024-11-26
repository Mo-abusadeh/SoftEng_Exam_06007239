package Shapes;

import java.awt.*;

public class Circle extends Shape {
    private int radius;


    public Circle(int radius, Point position, Color color) {
        super(position, color);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(position.x, position.y, radius, radius);

    }

    public int getRadius() {
        return radius;
    }

}
