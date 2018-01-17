package fr.bul.backend.model;

import org.json.JSONObject;

public class News implements JsonElement {
    private String _title;
    private String _description;
    private String _imageLink;

    public News(String title, String description, String imageLink) {
        _title = title;
        _description = description;
        _imageLink = imageLink;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("title", _title);
        json.put("description", _description);
        json.put("image", _imageLink);
        return json;
    }
}
