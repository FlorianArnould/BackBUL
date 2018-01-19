/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostDAO {

    private Connection con;

    public PostDAO() {
        con = SingletonConnection.getConnection();
    }

    public List<Post> getPosts(String rech) throws DAOException {
        String req = "select * from Post where LOWER(description) like ? OR LOWER(title) like ?";
        List<Post> list = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setString(1, "%" + rech.toLowerCase() + "%");
            ps.setString(2, "%" + rech.toLowerCase() + "%");
            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    GPSCoordinates gps = new GPSCoordinates(result.getFloat(6), result.getFloat(7));
                    Post p = new Post(result.getString(1), result.getString(2), result.getString(3), gps);
                    p.setPhone(result.getString(4));
                    p.setEmail(result.getString(5));
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cannot get Posts with figured name " + rech + " : " + e.getMessage());
        }
        return list;
    }

    public void createPost(Post p) throws DAOException {
        String req = "INSERT INTO post(title,description,name,phone,email,gpsLong,gbsLat) values (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getName());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getEmail());
            ps.setFloat(6, (float) p.getCoordinates().getLongitude());
            ps.setFloat(7, (float) p.getCoordinates().getLatitude());
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("Cannot insert the new post");
            }
        } catch (SQLException e) {
            throw new DAOException("An error occurred while adding new post :" + e.getMessage(), e);
        }
    }
}