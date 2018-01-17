package fr.bul.backend.activities;

import fr.bul.backend.dao.ActivityDAO;
import fr.bul.backend.dao.DAOException;
import fr.bul.backend.model.Activity;
import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.JsonElement;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import fr.bul.backend.model.ElementToSend
import fr.bul.backend.util.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivitiesService implements Route {
    public static final String LOCATION = "/activities";


    // categories: outdoor, cinema, monument, museum, shop, all
    @Override
    public Object handle(Request request, Response response) { //request = from front //response= to front
        try {
            JSONObject json = new JSONObject(request.body());
            int begin = json.getInt("begin");
            int end = json.getInt("end");
            JSONObject answer = new JSONObject();
            List<JsonElement> toSend = prepare_List_According_filter(json)
            for (JsonElement element : toSend) {
                answer.accumulate("activity", element.toJSON());
            }
            return answer.toString(4);
        } catch (DAOException e) {
            response.status(500);
            return "An error occurred";
        } catch (ActivityException e) {
            response.status(500);
            return "Category not designed";
        } catch (JSONException e) {
            response.status(400);
            return "Missing information";
        }
        return "ok";
    }


    private List<JsonElement> prepare_List_According_filter(JSONObject json) throws JSONException, ActivityException {
        String filter = json.getString("filter");
        ActivityDAO dao = new ActivityDAO();
        RSSNews rss = new RSSNews();
        GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));
        List<JsonElement> toSend = new ArrayList<>();
        switch (filter) {
            case "outdoor": //rss
                List<Activity> activityDAO = dao.getActivities(json.getString("search"));
                List<Activity> activityRSS= rss.getOutdoorNews();
                List<ElementToSend> elementsToSend_tmp = new ArrayList<>();
                toSend = toSendList(activityDAO,activityRSS);
                break;
            case "cinema": //rss
                List<Activity> activityDAO = dao.getActivities(json.getString("search"));
                List<Activity> activityRSS= rss.getCinemaNews();
                List<ElementToSend> elementsToSend_tmp = new ArrayList<>();
                toSend = toSendList(activityDAO,activityRSS);
                break;
            case "patrimony": //dao
                List<Activity> activityDAO = dao.getActivities(json.getString("search"));
                List<Activity> activityRSS= new List<Activity>(); // element kept empty for future extensions
                List<ElementToSend> elementsToSend_tmp = new ArrayList<>();
                toSend = toSendList(activityDAO,activityRSS);
                break;
            case "shop":
                List<Activity> activityDAO = dao.getActivities(json.getString("search"));
                List<Activity> activityRSS= new List<Activity>(); // element kept empty for future extensions
                List<ElementToSend> elementsToSend_tmp = new ArrayList<>();
                toSend = toSendList(activityDAO,activityRSS);
                break;
            case "all": //rs + dao
                List<Activity> activityDAO = dao.getActivities(json.getString("search"));
                List<Activity> activityRSS= rss.getAllNews();
                List<ElementToSend> elementsToSend_tmp = new ArrayList<>();
                toSend = toSendList(activityDAO,activityRSS);
                break;
                break;
            default:
                throw new ActivityException("The category " + filter + " does not exist");
                // this filter option doesn't exist
        }
        return toSend;
    }

    private List<JsonElement> toSendList(List<Activity> activityDAO, List<News> activityRSS)
    {
        List<ElementToSend> elementsToSend_tmp = new ArrayList<>();
        List<JsonElement> toSend = new ArrayList<>();
        for (Activity act : activityDAO.getActivities()) {
            elementsToSend_tmp.add(new ElementToSend(act, (int) Utils.distance(gps, act.getCoordinates())));
        }
        elementsToSend_tmp.sort((p1, p2) -> (p1.getDistance() - p2.getDistance()));
        elementsToSend_tmp.sort(new Comparator<ElementToSend>() {
            @Override
            public int compare(ElementToSend o1, ElementToSend o2) {
                return 0;
            }
        });
        List<Activity> toempty = activityRSS;
        for (int i=0; i<elementsToSend_tmp.size() ; i++)
        {
            toSend.add(elementsToSend_tmp.get(i));
            if (!toempty.isEmpty())
            {
                toSend.add(toSend.remove(0));
            }
        }
        toSend.addAll(toempty);
        return toSend();
    }
}
