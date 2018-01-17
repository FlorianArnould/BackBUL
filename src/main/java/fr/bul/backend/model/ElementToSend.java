package fr.bul.backend.model;

import org.json.JSONObject;



public class ElementToSend implements JsonElement {
    private JsonElement _element;
    private int _distance;

    public ElementToSend(JsonElement element, int distance) {
        _element = element;
        _distance = distance;
    }
    @Override
    public JSONObject toJSON() {
        JSONObject json = _element.toJSON();
        json.put("distance", _distance);
        return json;
    }
    public int getDistance() {
        return _distance;
    }
}
