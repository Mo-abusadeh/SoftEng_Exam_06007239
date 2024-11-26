import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Declare Park Member objects
        Keeper keeper = new Keeper("Geoff", "4392", 80);
        Lion lion = new Lion("Simba", 7, 30);
        Drone drone = new Drone("Monitor1", 128, 124);

        //lists of all the objects
        ArrayList<Keeper> keepers = new ArrayList<>();
        ArrayList<Lion> lions = new ArrayList<>();
        ArrayList<Drone> drones = new ArrayList<>();

        Map map = new Map(keepers, lions, drones);

        map.DisplayMap();



    }
}