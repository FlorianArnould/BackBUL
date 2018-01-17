package fr.bul.backend;

import fr.bul.backend.activities.ActivitiesService;
import fr.bul.backend.emergency.EmergencyService;
import fr.bul.backend.posts.AddPostsService;
import fr.bul.backend.posts.SearchPostService;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");
        post(ActivitiesService.LOCATION, new ActivitiesService());
        post(AddPostsService.LOCATION, new AddPostsService());
        post(SearchPostService.LOCATION, new SearchPostService());
        post(EmergencyService.LOCATION, new EmergencyService());

    }
}
