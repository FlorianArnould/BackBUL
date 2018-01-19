/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.model;
import fr.bul.backend.model.GPSCoordinates;
import org.json.JSONObject;

/**
 *
 * @author Samy
 */
public class Stat {
    private String _service;
    private GPSCoordinates _coordinates;
    private long _date ;

    public Stat(String _service, GPSCoordinates _coordinates, long _date) {
        this._service = _service;
        this._coordinates = _coordinates;
        this._date = _date;
    }
    
    

    public String getService() {
        return _service;
    }

    public void setService(String service) {
        this._service = service;
    }

    public GPSCoordinates getCoordinates() {
        return _coordinates;
    }

    public void setCoordinates(GPSCoordinates coordinates) {
        this._coordinates = coordinates;
    }

    public long getDate() {
        return _date;
    }

    public void setDate(long date) {
        this._date = date;
    }
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("service", _service);
        json.put("date", _date);
        json.put("latitude",_coordinates.getLatitude());
        json.put("longitude",_coordinates.getLongitude());
        return json;
    }
}
