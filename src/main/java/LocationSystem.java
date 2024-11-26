import com.google.gson.Gson;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;;

public class LocationSystem {
    // getCoords returns am AWT Point object that contains the co-ordinates of the GPS tag 'GPSid' passed in
    // It can return null if it can't find the tag
    // It can throw an Exception if it can't connect to the GPS system
    public static Point getCoords(int GPSid) throws IOException {
        try    {
            // Connect to the location tracker system
            URL myURL = new URL("https://liontracker-2342d1c9d397.herokuapp.com/coords?tag="+GPSid);
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myURL.openStream()));

            String inputLine;
            String responseBody = new String();
            // Read the body of the response
            while ((inputLine = in.readLine()) != null) {
                responseBody+=inputLine;
            }
            in.close();
            // Convert from json
            Gson gson=new Gson();
            return gson.fromJson(responseBody,Point.class);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}