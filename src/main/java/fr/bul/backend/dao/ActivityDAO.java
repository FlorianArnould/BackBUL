/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.Activity;
import fr.bul.backend.model.Category;
import fr.bul.backend.model.GPSCoordinates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Samy
 */
public class ActivityDAO {

    private Connection con;
    private CategoryDAO catDAO = null;

    public ActivityDAO() {
        this.con = SingletonConnection.getConnection();
    }

    public ArrayList<Activity> getActivities(String rech) throws DAOException {
        String req = "SELECT * from activity as a join category as c on a.idcat=c.id where a.description like ? ";
        ArrayList<Activity> listeA = new ArrayList<Activity>();
        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setString(1, "%" + rech + "%");
            try (ResultSet resultat = ps.executeQuery()) {
                while (resultat.next()) {
                    GPSCoordinates gps = new GPSCoordinates(resultat.getFloat(8), resultat.getFloat(9));
                    catDAO = new CategoryDAO();

                    Category cat = new Category(resultat.getInt(2), resultat.getString(12));
                    //Category cat = new Category(resultat.getInt("idcat"), resultat.getString("category.name"));

                    Activity a = new Activity(resultat.getString(3), resultat.getString(4), resultat.getString(5), gps, cat);

                    a.setPhone(resultat.getString("phone"));
                    a.setEmail(resultat.getString("email"));
                    a.setUrl_img(resultat.getString("url_image"));

                    listeA.add(a);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Posts with figured name" + e.getMessage());
        }
        return listeA;
    }

    public ArrayList<Activity> getActivities(String rech, String categ) throws DAOException {
        String req = "SELECT * from activity as a join category as c on a.idcat=c.id where a.description like ? and c.name = ?";


        ArrayList<Activity> listeA = new ArrayList<Activity>();
        try (PreparedStatement ps = con.prepareStatement(req)) {

            ps.setString(1, "%" + rech + "%");
            ps.setString(2, categ);

            try (ResultSet resultat = ps.executeQuery()) {
                while (resultat.next()) {

                    GPSCoordinates gps = new GPSCoordinates(resultat.getFloat(8), resultat.getFloat(9));
                    catDAO = new CategoryDAO();

                    Category cat = new Category(resultat.getInt(2), resultat.getString(12));


                    Activity a = new Activity(resultat.getString(3), resultat.getString(4), resultat.getString(5), gps, cat);

                    a.setPhone(resultat.getString("phone"));
                    a.setEmail(resultat.getString("email"));
                    a.setUrl_img(resultat.getString("url_image"));

                    listeA.add(a);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Activities with figured name" + e.getMessage());
        }
        return listeA;
    }

}
