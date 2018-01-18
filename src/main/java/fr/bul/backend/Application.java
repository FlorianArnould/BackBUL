package fr.bul.backend;

import fr.bul.backend.activities.ActivitiesService;
import fr.bul.backend.emergency.EmergencyService;
import fr.bul.backend.posts.AddPostsService;
import fr.bul.backend.posts.SearchPostService;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(8080);
        externalStaticFileLocation("/home/administrateur/img");
        enableCORS("*", "POST");
        post(ActivitiesService.LOCATION, "*/*", new ActivitiesService());
        get(ActivitiesService.LOCATION, "*/*", (request, response) -> "");
        post(AddPostsService.LOCATION, "*/*", new AddPostsService());
        get(AddPostsService.LOCATION, "*/*", (request, response) -> "");
        post(SearchPostService.LOCATION, "*/*", new SearchPostService());
        post(EmergencyService.LOCATION, "*/*", new EmergencyService());
        get(EmergencyService.LOCATION, "*/*", (request, response) -> "");
    }

    private static void enableCORS(final String origin, final String methods) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            //response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
