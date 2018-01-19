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
import java.util.List;


public class ActivityDAO {

    private Connection con;

    public ActivityDAO() {
        this.con = SingletonConnection.getConnection();
    }

    public List<Activity> getActivities(String search) throws DAOException {
        String req = "SELECT * from activity as a join category as c on a.idcat=c.id where a.description like ? OR a.title like ?";
        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            return execute(ps);
        } catch (SQLException e) {
            throw new DAOException("Error cannot get Posts with figured name " + search + " : " + e.getMessage());
        }
    }

    public List<Activity> getActivities(String search, String category) throws DAOException {
        String req = "SELECT * from activity as a join category as c on a.idcat=c.id where (a.description like ? or a.title like ?) and c.name = ?";

        try (PreparedStatement ps = con.prepareStatement(req)) {

            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, category);
            return execute(ps);
        } catch (SQLException e) {
            throw new DAOException("Error cant get Activities with figured name" + e.getMessage());
        }
    }

    private List<Activity> execute(PreparedStatement ps) throws SQLException {
        List<Activity> list = new ArrayList<>();
        try (ResultSet result = ps.executeQuery()) {
            while (result.next()) {

                GPSCoordinates gps = new GPSCoordinates(result.getFloat(8), result.getFloat(9));

                Category cat = new Category(result.getInt(2), result.getString(12));

                Activity a = new Activity(result.getString(3), result.getString(4), result.getString(5), gps, cat);

                a.setPhone(result.getString("phone"));
                a.setEmail(result.getString("email"));
                a.setImageLink(result.getString("url_image"));

                list.add(a);
            }
        }
        return list;
    }
}
