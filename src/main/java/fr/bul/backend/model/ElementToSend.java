package fr.bul.backend.model;

import org.json.JSONObject;


public class ElementToSend implements JsonElement {
    private JsonElement element;
    private int distance;

    public ElementToSend(JsonElement element, int distance) {
        this.element = element;
        this.distance = distance;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = element.toJSON();
        json.put("distance", distance);
        return json;
    }

    public int getDistance() {
        return distance;
    }
}
