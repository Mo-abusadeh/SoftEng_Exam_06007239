public class Keeper extends ParkMembers{

    //fields
    private String name;
    private String phone_num;

    //constructor
    public Keeper(String name, String phone_num, int GPS_tag) {
        super(GPS_tag);
        this.name = name;
        this.phone_num = phone_num;
    }

    //getters
    public String getName() {return name;}
    public String getPhone_num() {return phone_num;}
    public int getGPS_tag() {return GPS_tag;}
}
