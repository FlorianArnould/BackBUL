package fr.bul.backend.util;

import fr.bul.backend.model.GPSCoordinates;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate the Utils class");
    }

    public static double distance(GPSCoordinates gps1, GPSCoordinates gps2) {
        double theta = gps1.getLongitude() - gps2.getLongitude()
        double dist = Math.sin(deg2rad(gps1.getLatitude())) * Math.sin(deg2rad(gps2.getLatitude())) + Math.cos(deg2rad(gps1.getLatitude())) * Math.cos(deg2rad(gps2.getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
