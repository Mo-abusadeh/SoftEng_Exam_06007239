public class Drone extends ParkMembers{
    // Fields
    private String name;
    private int radio_freq;

    // Constructor
    public Drone(String name, int radio_freq, int GPS_tag) {
        super(GPS_tag);
        this.name = name;
        this.radio_freq = radio_freq;
    }

    // Getters
    public String getName() {return name;}
    public int getRadio_freq() {return radio_freq;}
    public int getGPS_tag() {return GPS_tag;}
}
