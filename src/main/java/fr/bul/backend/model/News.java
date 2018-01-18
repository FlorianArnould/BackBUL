package fr.bul.backend.model;

import org.json.JSONObject;

public class News implements JsonElement {
    private String title;
    private String description;
    private String imageLink;

    public News(String title, String description, String imageLink) {
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("image", imageLink);
        return json;
    }
}
