package fr.bul.backend.activities;

import fr.bul.backend.model.News;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSNews {
    private List<News> outdoor;
    private List<News> cinema;

    public void refresh() throws RSSActivitiesException {
        JSONObject outdoorJson = load("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fcaen.maville.com%2Fflux%2Frss%2Factu.php%3Fxtor%3DRSS-18%26c%3Dsortir%26code%3Dca");
        JSONObject cinemaJson = load("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fcaen.maville.com%2Fflux%2Frss%2Factu.php%3Fxtor%3DRSS-18%26c%3Dcinema%26code%3Dca");
        outdoor = parse(outdoorJson);
        cinema = parse(cinemaJson);
    }

    private JSONObject load(String url) throws RSSActivitiesException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15 * 1000);
            connection.connect();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                return new JSONObject(stringBuilder.toString());
            }
        } catch (IOException e) {
            throw new RSSActivitiesException("Cannot get the RSS from : " + url, e);
        }
    }

    private List<News> parse(JSONObject json) {
        List<News> list = new ArrayList<>();
        JSONArray array = json.getJSONArray("items");
        for (Object object : array) {
            JSONObject jsonObject = (JSONObject) object;
            String title = jsonObject.getString("title");
            String description = jsonObject.getString("description");
            String image = jsonObject.getJSONObject("enclosure").getString("link");
            list.add(new News(title, description, image));
        }
        return list;
    }

    public List<News> getOutdoorNews() {
        return outdoor;
    }

    public List<News> getCinemaNews() {
        return cinema;
    }

    public List<News> getAllNews() {
        List<News> all = new ArrayList<>();
        all.addAll(outdoor);
        all.addAll(cinema);
        return all;
    }
}
