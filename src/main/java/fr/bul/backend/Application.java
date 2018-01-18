package fr.bul.backend;

import fr.bul.backend.activities.ActivitiesService;
import fr.bul.backend.emergency.EmergencyService;
import fr.bul.backend.posts.AddPostsService;
import fr.bul.backend.posts.SearchPostService;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");
        post(ActivitiesService.LOCATION, "*/*", new ActivitiesService());
        get(ActivitiesService.LOCATION, (request, response) -> "");
        post(AddPostsService.LOCATION, "*/*", new AddPostsService());
        get(AddPostsService.LOCATION, (request, response) -> "");
        post(SearchPostService.LOCATION, "*/*", new SearchPostService());
        get(SearchPostService.LOCATION, (request, response) -> "");
        post(EmergencyService.LOCATION, "*/*", new EmergencyService());
        get(EmergencyService.LOCATION, (request, response) -> "");
    }
}
