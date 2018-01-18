/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.model;

import org.json.JSONObject;


public class Activity implements JsonElement {
    private Category category;
    private String title;
    private String description;
    private String name;
    private String phone;
    private String email;
    private GPSCoordinates coordinates;
    private String imageLink;

    public Activity(String title, String description, String name, GPSCoordinates coordinates, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.name = name;
        this.coordinates = coordinates;
        email = null;
        phone = null;
        imageLink = null;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Category getCategory() {
        return category;
    }

    public GPSCoordinates getCoordinates() {
        return coordinates;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }


    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("category", category.getName());
        json.put("description", description);
        json.put("name", name);
        json.put("phone", phone);
        json.put("email", email);
        json.put("imageLink", imageLink);
        json.put("latitude",coordinates.getLatitude());
        json.put("longitude",coordinates.getLongitude());
        return json;
    }
}
