/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Post;

/**
 *
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
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Post p = new Post(resultat.getString(2), resultat.getString(3), resultat.getString(4));
                System.out.println(p);
                listeP.add(p);
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Posts" + e.getMessage());
        }
        return listeP;
    }

    public ArrayList<Post> getPosts(String rech) throws DAOException {
        String req = "select * from Post where description like ? ";
        ArrayList<Post> listeP = new ArrayList<Post>();
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, "%" + rech + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Post p = new Post(resultat.getString(1),resultat.getString(2), resultat.getString(3), resultat.getString(4),resultat.getString(5));
                System.out.println(p);
                listeP.add(p);
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Posts with figured name" + e.getMessage());
        }
        return listeP;
    }
    
    public void createPost(Post p) throws DAOException {
        String req = "INSERT INTO post(title,description,name,phone,email) values (?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            preparedStatement.setString(1, p.getTitle());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setString(3, p.getName());
            preparedStatement.setString(4, p.getTel());
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
