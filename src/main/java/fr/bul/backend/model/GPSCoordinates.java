package fr.bul.backend.model;

public class GPSCoordinates {
    private double _latitude;
    private double _longitude;

    public GPSCoordinates(double latitude, double longitude) {
        _latitude = latitude;
        _longitude = longitude;
    }

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

}
