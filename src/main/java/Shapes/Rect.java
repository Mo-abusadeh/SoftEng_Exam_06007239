package Shapes;

import java.awt.*;

public class Rect extends Shape {
    private int width;
    private int height;


    public Rect(int width, int height, Point position, Color color) {
        super(position, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(position.x, position.y, width, height);
    }


}
