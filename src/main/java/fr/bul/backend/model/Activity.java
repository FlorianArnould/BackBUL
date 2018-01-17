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
public class Activity implements JsonElement {
    private Category _category;
    private String _title;
    private String _description;
    private String _name;
    private String _phone;
    private String _email;
    private GPSCoordinates _coordinates;
    private String _url_img;

    public Activity(String title, String description, String name, GPSCoordinates coordinates, Category category) {
        _title = title;
        _description = description;
        _category = category;
        _name = name;
        _coordinates = coordinates;
        _email = null;
        _phone = null;
        _url_img = null;
    }

    public void setPhone(String _phone) {
        this._phone = _phone;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public Category getCategory() {
        return _category;
    }

    public GPSCoordinates getCoordinates() {
        return _coordinates;
    }

    public void setUrl_img(String _url_img) {
        this._url_img = _url_img;
    }


    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("title", _title);
        json.put("category", _category.getName());
        json.put("description", _description);
        json.put("name", _name);
        json.put("phone", _phone);
        json.put("email", _email);
        json.put("url_img", _url_img);
        return json;
    }
}
