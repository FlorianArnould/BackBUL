/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.model;

import org.json.JSONObject;

/**
 * @author Samy
 */
public class Post implements JsonElement {

    private String _title;
    private String _description;
    private String _name;
    private String _phone;
    private String _email;
    private GPSCoordinates _coordinates;

    public Post(String title, String description, String name, GPSCoordinates coordinates) {
        _title = title;
        _description = description;
        _name = name;
        _coordinates = coordinates;
        _email = null;
        _phone = null;
    }


    public String getTitle() {
        return _title;
    }

    public String getDescription() {
        return _description;
    }

    public String getName() {
        return _name;
    }

    public GPSCoordinates getCoordinates() {
        return _coordinates;
    }

    public void setPhone(String phone) {
        _phone = phone;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("title", _title);
        json.put("description", _description);
        json.put("name", _name);
        json.put("phone", _phone);
        json.put("email", _email);
        return json;
    }
}
