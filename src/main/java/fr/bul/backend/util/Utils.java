package fr.bul.backend.util;

import fr.bul.backend.model.GPSCoordinates;
import spark.Response;

public class Utils {
    private static final double R = 6378.137;

    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate the Utils class");
    }

    public static double distance(GPSCoordinates gps1, GPSCoordinates gps2) {
        double dLat = gps2.getLatitude() * Math.PI / 180 - gps1.getLatitude() * Math.PI / 180;
        double dLon = gps2.getLongitude() * Math.PI / 180 - gps1.getLongitude() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(gps1.getLatitude() * Math.PI / 180) * Math.cos(gps2.getLatitude() * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }
    public static void setHeader(Response response){
        response.header("Access-Control-Allow-Origin", "*");
    }
}
