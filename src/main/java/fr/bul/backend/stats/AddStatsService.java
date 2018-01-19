package fr.bul.backend.stats;

import fr.bul.backend.dao.DAOException;
import fr.bul.backend.dao.StatsDao;
import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.Stat;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddStatsService implements Route {
    public static final String LOCATION = "/addStats";
    private static final Logger LOGGER = LoggerFactory.getLogger(AddStatsService.class);

    @Override
    public Object handle(Request request, Response response) {
        try {
            JSONObject json = new JSONObject(request.body());
            StatsDao dao = new StatsDao();
            GPSCoordinates gps = new GPSCoordinates(json.getDouble("latitude"), json.getDouble("longitude"));
            String service = json.getString("service");
            long timestamp = json.getInt("timestamp");
            dao.addStat(new Stat(service, gps, timestamp));
            JSONObject answer = new JSONObject();
            answer.put("success", true);
            return answer.toString(4);
        } catch (JSONException e) {
            LOGGER.error("JSON error : " + e.getMessage(), e);
            response.status(400);
            return "Missing information";
        } catch (DAOException e) {
            LOGGER.error("DAO error : " + e.getMessage(), e);
            response.status(500);
            return "An error occurred";
        }
    }
}
