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
public class Activity extends Post {
    private Category _category;


    public Activity(String title, String description, String name, GPSCoordinates coordinates, Category category) {
        super(title, description, name, coordinates);
        _category = category;
    }


    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("category", _category.getName());
        return json;
    }
}
