package fr.bul.backend.activities;

import fr.bul.backend.dao.ActivityDAO;
import fr.bul.backend.dao.DAOException;
import fr.bul.backend.model.*;
import fr.bul.backend.posts.AddPostsService;
import fr.bul.backend.util.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivitiesService implements Route {
    public static final String LOCATION = "/activities";
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiesService.class);


    // categories: outdoor, cinema, monument, museum, shop, all
    @Override
    public Object handle(Request request, Response response) { //request = from front //response= to front
        try {
            JSONObject json = new JSONObject(request.body());
            int begin = json.getInt("begin");
            int end = json.getInt("end");
            JSONObject answer = new JSONObject();
            List<JsonElement> toSend = prepare_List_According_filter(json);
            toSend= toSend.subList(Math.min(begin, toSend.size() - 1), Math.min(end, toSend.size()));
            for (JsonElement element : toSend) {
                answer.accumulate("activity", element.toJSON());
            }
            return answer.toString(4);
        } catch (DAOException e) {
            LOGGER.error("DAO error : " +e.getMessage(), e);
            response.status(500);
            return "An error occurred";
        } catch (ActivityException e) {
            LOGGER.error("Category not designed : " +e.getMessage(), e);
            response.status(500);
            return "Category not designed";
        } catch (JSONException e) {
            LOGGER.error("JSON error : " +e.getMessage(), e);
            response.status(400);
            return "Missing information";
        }
    }


    private List<JsonElement> prepare_List_According_filter(JSONObject json) throws JSONException, ActivityException, DAOException {
        String filter = json.getString("filter");
        ActivityDAO dao = new ActivityDAO();
        RSSNews rss = new RSSNews();
        try {
            rss.refresh();
        }catch(RSSActivitiesException e){
            e.printStackTrace();
        }
        
        GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));
        List<Activity> activityDAO;
        List<News> activityRSS;
        switch (filter) {
            case "outdoor": //rss
                activityDAO = dao.getActivities(json.getString("search"), filter);
                activityRSS = rss.getOutdoorNews();
                break;
            case "cinema": //rss
                activityDAO = dao.getActivities(json.getString("search"), filter);
                activityRSS = rss.getCinemaNews();
                break;
            case "monument": //dao
                activityDAO = dao.getActivities(json.getString("search"), filter);
                activityRSS = new ArrayList<>(); // element kept empty for future extensions
                break;
            case "shop":
                activityDAO = dao.getActivities(json.getString("search"), filter);
                activityRSS = new ArrayList<>(); // element kept empty for future extensions
                break;
            case "all": //rs + dao
                activityDAO = dao.getActivities(json.getString("search"));
                activityRSS = rss.getAllNews();
                break;
            default:
                throw new ActivityException("The category " + filter + " does not exist");
                // this filter option doesn't exist
        }
        return toSendList(activityDAO, activityRSS, gps);
    }

    private List<JsonElement> toSendList(List<Activity> activityDAO, List<News> activityRSS, GPSCoordinates gps) {
        List<ElementToSend> tmpElementsToSend = new ArrayList<>();
        List<JsonElement> toSend = new ArrayList<>();
        for (Activity act : activityDAO) {
            tmpElementsToSend.add(new ElementToSend(act, (int) Utils.distance(gps, act.getCoordinates())));
        }
        tmpElementsToSend.sort((p1, p2) -> (p1.getDistance() - p2.getDistance()));
        if(activityRSS == null){
            LOGGER.info("null");
        }
        LOGGER.info(LOCATION);
        List<JsonElement> toEmpty = new ArrayList<>(activityRSS);
        for (ElementToSend tmpElementToSend : tmpElementsToSend) {
            toSend.add(tmpElementToSend);
            if (!toEmpty.isEmpty()) {
                toSend.add(toSend.remove(0));
            }
        }
        toSend.addAll(toEmpty);
        return toSend;
    }
}
