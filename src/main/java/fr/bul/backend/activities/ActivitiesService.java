package fr.bul.backend.activities;

import fr.bul.backend.model.GPSCoordinates;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class ActivitiesService implements Route {
    public static final String LOCATION = "/activities";

    @Override
    public Object handle(Request request, Response response) {
        try {
            JSONObject json = new JSONObject(request.body());
            GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));

        } catch (JSONException e) {

        }
        return "ok";
    }
}
