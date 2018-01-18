/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.model;

import org.json.JSONObject;


public class Post implements JsonElement {

    private String title;
    private String description;
    private String name;
    private String phone;
    private String email;
    private GPSCoordinates coordinates;

    public Post(String title, String description, String name, GPSCoordinates coordinates) {
        this.title = title;
        this.description = description;
        this.name = name;
        this.coordinates = coordinates;
        email = null;
        phone = null;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public GPSCoordinates getCoordinates() {
        return coordinates;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("name", name);
        json.put("phone", phone);
        json.put("email", email);
        json.put("latitude",coordinates.getLatitude());
        json.put("longitude",coordinates.getLongitude());
        return json;
    }
}
