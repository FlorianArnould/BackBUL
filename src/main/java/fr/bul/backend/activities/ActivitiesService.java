package fr.bul.backend.activities;

import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class ActivitiesService implements Route {
    public static final String LOCATION = "/activities";

    @Override
    public Object handle(Request request, Response response) {
        JSONObject json = new JSONObject(request.body());

        return "ok";
    }
}
