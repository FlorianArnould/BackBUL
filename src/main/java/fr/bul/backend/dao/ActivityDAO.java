/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.Activity;

import java.util.ArrayList;

/**
 * @author Samy
 */

public interface ActivityDAO {
    public ArrayList<Activity> getActivities();
}
