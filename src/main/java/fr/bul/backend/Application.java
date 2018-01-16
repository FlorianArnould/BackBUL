package fr.bul.backend;

import fr.bul.backend.activities.ActivitiesService;
import fr.bul.backend.posts.AddPostsService;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(4000);
        staticFileLocation("/public");
        get(ActivitiesService.LOCATION, new ActivitiesService());
        post(AddPostsService.LOCATION, new AddPostsService());
    }
}
