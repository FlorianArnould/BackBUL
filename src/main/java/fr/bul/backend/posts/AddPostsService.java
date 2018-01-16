package fr.bul.backend.posts;

import model.Post;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddPostsService implements Route {
    public static final String LOCATION = "/addPost";

    @Override
    public Object handle(Request request, Response response){
        // TODO: 16/01/18 remove the ID to the Post class
        try {
            JSONObject json = new JSONObject(request.body());
            Post post = new Post(0, json.getString("title"), json.getString("description"), json.getString("name"));
            if (request.queryParams("id").isEmpty()) {
                return "empty string";
            }
            post.setTel(json.optString("phone", null));
        }catch (JSONException e){
            response.status(400);
            return "Missing information";
        }
        return "param : " + request.queryParams("id");
    }
}
