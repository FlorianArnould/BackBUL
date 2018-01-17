package fr.bul.backend.emergency;

import fr.bul.backend.model.GPSCoordinates;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class EmergencyService implements Route {
    public static final String LOCATION = "/emergency";
    @Override
    public Object handle(Request request, Response response) {
        try {
            JSONObject json = new JSONObject(request.body());
            GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));
            String alertCategory = json.getString("category");
            // TODO: 17/01/18 Log the alert
        } catch (JSONException e) {
            response.status(400);
            return "Missing information";
        }
        JSONObject json = new JSONObject();
        json.put("success", "True");
        return json.toString(4);
    }
}
