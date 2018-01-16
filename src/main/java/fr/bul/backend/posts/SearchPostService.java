package fr.bul.backend.posts;

import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class SearchPostService implements Route{

    @Override
    public Object handle(Request request, Response response){
        JSONObject json = new JSONObject(request.body());
        String search = request.queryParams("search");
        int begin = Integer.parseInt(request.queryParams("begin"));
        int end = Integer.parseInt(request.queryParams("end"));
        return null;
    }
}
