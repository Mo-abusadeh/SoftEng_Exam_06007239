import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Shapes.Circle;
import Shapes.Shape;
import Shapes.Square;


public class Map {
    //Fields
    private ArrayList<Keeper> keepers;
    private ArrayList<Lion> lions;
    private ArrayList<Drone> drones;

    //Constructor
    public Map(ArrayList<Keeper> keepers, ArrayList<Lion> lions, ArrayList<Drone> drones) {
        this.keepers = keepers;
        this.lions = lions;
        this.drones = drones;
    }

    public void DisplayMap() {
        int mapXdim = 600;
        int mapYdim = 600;

        // Keepers shape details
        Color keeper_color = new Color(0, 0, 255);
        int keeper_radius = 2;

        // Lions shape details
        Color lion_color = new Color(255, 0, 0);
        int lion_radius = 3;

        // Drones shape details
        Color drone_color = new Color(0, 0, 0);
        int drone_edge = 3;

        // Location arrays
        ArrayList<Point> keeper_locs = new ArrayList<>();
        ArrayList<Point> lion_locs = new ArrayList<>();
        ArrayList<Point> drone_locs = new ArrayList<>();

        //Name arrays
        ArrayList<String> keeper_names = new ArrayList<>();
        ArrayList<String> lion_names = new ArrayList<>();
        ArrayList<String> drone_names = new ArrayList<>();

        // arrays for the alert system
        ArrayList<String> phone_numbers = new ArrayList<>();
        ArrayList<Integer> Freqs = new ArrayList<>();

        // Create a new window
        Frame frame1 = new Frame("Map");

        while (frame1.isActive()){
            // Get locations and names for the drawing class
            for (Keeper keeper : keepers) {
                Point keeper_coord = LocationSystem.getCoords(keeper.getGPS_tag()); //Throws exception (can't connect to system)
                keeper_locs.add(keeper_coord);
                phone_numbers.add(keeper.getPhone_num());
                keeper_names.add(keeper.getName());
            }
            for (Lion lion : lions) {
                Point lion_coord = LocationSystem.getCoords(lion.getGPS_tag()); //Throws exception (can't connect to system)
                lion_locs.add(lion_coord);

                lion_names.add(lion.getName());
            }

            for (Drone drone : drones) {
                Point drone_coord = LocationSystem.getCoords(drone.getGPS_tag()); //Throws exception (can't connect to system)
                drone_locs.add(drone_coord);
                Freqs.add(drone.getRadio_freq());
                drone_names.add(drone.getName());
            }


            // Check if a lion is near a keeper
            for (Point lion_loc : lion_locs) {
                for (Point keeper_loc : keeper_locs) {
                    if (isNear(lion_loc, keeper_loc)){
                        AlertSystem alert = new AlertSystem();
                        alert.alertAKeeper(phone_numbers.get(keeper_locs.indexOf(keeper_loc)));
                        alert.alertADrone(Freqs.get(drone_locs.indexOf(keeper_loc)));
                    }
                }
            }


            // Add shapes to the map
            ArrayList<Shape> shapes = new ArrayList<>();
            for (Point keeper_loc : keeper_locs) {
                Circle circle = new Circle(keeper_radius, keeper_loc, keeper_color) {
                    @Override
                    public void draw(Graphics g){
                        g.setColor(color);
                        g.fillOval(position.x, position.y, circle.getRadius(), circle.getRadius());
                        g.drawString(keeper_names.get(keeper_locs.indexOf(keeper_loc)), position.x, position.y);
                        g.drawString(phone_numbers.get(keeper_locs.indexOf(keeper_loc)), position.x, position.y);
                    }
                };
                shapes.add(circle);
            }
            for (Point lion_loc : lion_locs) {
                Circle circle = new Circle(lion_radius, lion_loc, lion_color) {
                    @Override
                    public void draw(Graphics g){
                        g.setColor(color);
                        g.fillOval(position.x, position.y, circle.getRadius(), circle.getRadius());
                        g.drawString(lion_names.get(lion_locs.indexOf(lion_loc)), position.x, position.y);
                    }
                };
                shapes.add(circle);
            }
            for (Point drone_loc : drone_locs) {
                Square square = new Square(drone_edge, drone_loc, drone_color) {
                    @Override
                    public void draw(Graphics g){
                        g.setColor(color);
                        g.fillRect(position.x, position.y, square.getEdge(), square.getEdge());
                        g.drawString(drone_names.get(drone_locs.indexOf(drone_loc)), position.x, position.y);
                        g.drawString(Freqs.get(drone_locs.indexOf(drone_loc)).toString(), position.x, position.y);
                    }
                };
                shapes.add(square);
            }

            Drawing drawing1 = new Drawing(shapes);// Create a new drawing

            frame1.add(drawing1); // Add the drawing to the frame
            drawing1.setSize(mapXdim, mapYdim); // Set the size of the drawing
            drawing1.setBackground(Color.WHITE); // Set the background color of the drawing

            // Set the layout of the frame
            frame1.setLayout(new BorderLayout());
            frame1.add(drawing1, BorderLayout.CENTER);
            frame1.setSize(mapXdim, mapYdim); // Set the size of the frame
            frame1.setVisible(true); // Make the frame visible
            frame1.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    frame1.dispose();
                }
            });



            drawing1.repaint(); // Repaint the drawing

            try{Thread.sleep(1000);}
            catch(Exception e){}
        }

    }

    public boolean isNear(Point lion_loc, Point keeper_loc){
        double distance = Math.sqrt(Math.pow(lion_loc.x - keeper_loc.x, 2) + Math.pow(lion_loc.y - keeper_loc.y, 2));
        if (distance < 60.0){
            return true;
        }
        return false;
    }



}
