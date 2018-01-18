package fr.bul.backend.emergency;

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
            // Check if we get all needed information for the rescue
            if (!json.has("latitude") || json.has("longitude") || json.has("category")) {
                response.status(400);
                return "Missing information";
            }
            // This functionality is not already implemented but it will be needed here to contact the rescue services
            JSONObject answer = new JSONObject();
            answer.put("success", "True");
            return answer.toString(4);
        } catch (JSONException e) {
            response.status(400);
            return "Missing information";
        }
    }
}
