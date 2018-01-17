package fr.bul.backend.posts;

import fr.bul.backend.dao.DAOException;
import fr.bul.backend.dao.PostDAO;
import fr.bul.backend.model.ElementToSend;
import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.Post;
import fr.bul.backend.util.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class SearchPostService implements Route {
    public static final String LOCATION = "/searchPost";

    @Override
    public Object handle(Request request, Response response) {
        try {
            JSONObject json = new JSONObject(request.body());
            int begin = json.getInt("begin");
            int end = json.getInt("end");
            GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));
            PostDAO dao = new PostDAO();
            List<Post> posts = dao.getPosts(json.getString("search"));
            List<ElementToSend> elementsToSend = new ArrayList<>();
            for (Post post : posts) {
                elementsToSend.add(new ElementToSend(post, (int) Utils.distance(gps, post.getCoordinates())));
            }
            elementsToSend.sort((p1, p2) -> (p1.getDistance() - p2.getDistance()));
            List<Post> postsToSend = posts.subList(Math.min(begin, posts.size() - 1), Math.min(end, posts.size()));
            JSONObject answer = new JSONObject();
            for (Post post : postsToSend) {
                answer.accumulate("posts", post.toJSON());
            }
            return answer.toString(4);
        } catch (DAOException e) {
            response.status(500);
            return "An error occurred";
        } catch (JSONException e) {
            response.status(400);
            return "Missing information";
        }
    }
}
