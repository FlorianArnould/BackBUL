package fr.bul.backend.activities;

import spark.Request;
import spark.Response;
import spark.Route;

public class ActivitiesService implements Route {
    public static final String LOCATION = "/activities";

    @Override
    public Object handle(Request request, Response response) {
        return "ok";
    }
}
