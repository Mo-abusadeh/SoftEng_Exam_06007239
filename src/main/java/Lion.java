public class Lion extends ParkMembers{

    // Fields
    private String name;
    private int age;


    // Constructor
    public Lion(String name, int age, int GPS_tag) {
        super(GPS_tag);
        this.name = name;
        this.age = age;

    }

    // Getters
    public String getName() {return name;}
    public int getAge() {return age;}
    public int getGPS_tag() {return GPS_tag;}
}
