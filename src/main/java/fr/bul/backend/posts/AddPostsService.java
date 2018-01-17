package fr.bul.backend.posts;

import fr.bul.backend.dao.DAOException;
import fr.bul.backend.dao.PostDAO;
import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.Post;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddPostsService implements Route {
    public static final String LOCATION = "/addPost";

    @Override
    public Object handle(Request request, Response response) {
        try {
            JSONObject json = new JSONObject(request.body());
            GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));
            Post post = new Post(json.getString("title"), json.getString("description"), json.getString("name"), gps);
            if (request.queryParams("id").isEmpty()) {
                return "empty string";
            }
            post.setPhone(json.optString("phone", null));
            PostDAO dao = new PostDAO();
            dao.createPost(post);
            JSONObject answer = new JSONObject();
            answer.put("success", true);
            return answer.toString(4);
        } catch (JSONException e) {
            response.status(400);
            return "Missing information";
        } catch (DAOException e) {
            response.status(500);
            return "An error occurred";
        }
    }
}
