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

/**
 * @author Samy
 */
public class PostDAO {

    private Connection con;

    public PostDAO() {
        con = SingletonConnection.getConnection();
    }

    public ArrayList<Post> getPosts() throws DAOException {
        String req = "select * from Post";
        ArrayList<Post> listeP = new ArrayList<Post>();
        try (PreparedStatement ps = con.prepareStatement(req);) {
            try (ResultSet resultat = ps.executeQuery()) {
                while (resultat.next()) {
                    // TODO: 17/01/18 add the real coordinates
                    GPSCoordinates gps = new GPSCoordinates(0, 0);
                    Post p = new Post(resultat.getString(2), resultat.getString(3), resultat.getString(4), gps);
                    p.setPhone(resultat.getString(4));
                    p.setEmail(resultat.getString(5));
                    listeP.add(p);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Posts" + e.getMessage());
        }
        return listeP;
    }

    public ArrayList<Post> getPosts(String rech) throws DAOException {
        String req = "select * from Post where description like ? ";
        ArrayList<Post> listeP = new ArrayList<Post>();
        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setString(1, "%" + rech + "%");
            try (ResultSet resultat = ps.executeQuery()) {
                while (resultat.next()) {
                    GPSCoordinates gps = new GPSCoordinates(resultat.getFloat(6), resultat.getFloat(7));
                    Post p = new Post(resultat.getString(1), resultat.getString(2), resultat.getString(3), gps);
                    p.setPhone(resultat.getString(4));
                    p.setEmail(resultat.getString(5));
                    listeP.add(p);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Posts with figured name" + e.getMessage());
        }
        return listeP;
    }

    public void createPost(Post p) throws DAOException {
        String req = "INSERT INTO post(title,description,name,phone,email) values (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setString(1, p.getTitle());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setString(3, p.getName());
            preparedStatement.setString(4, p.getPhone());
            preparedStatement.setString(5, p.getEmail());
            int result = preparedStatement.executeUpdate();
            if (result != 1) {
                throw new DAOException("Cannot insert the new post");
            }
        } catch (SQLException e) {
            throw new DAOException("An error occurred :" + e.getMessage(), e);
        }
    }
}