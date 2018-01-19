package fr.bul.backend.stats;

import fr.bul.backend.dao.DAOException;
import fr.bul.backend.dao.StatsDao;
import fr.bul.backend.model.Stat;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class GetStatsService implements Route {
    public static final String LOCATION = "/getStats";
    private static final Logger LOGGER = LoggerFactory.getLogger(GetStatsService.class);

    @Override
    public Object handle(Request request, Response response) {
        try {
            StatsDao dao = new StatsDao();
            List<Stat> stats = dao.getStats();
            JSONArray answer = new JSONArray();
            for (Stat stat : stats) {
                answer.put(stat.toJSON());
            }
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
