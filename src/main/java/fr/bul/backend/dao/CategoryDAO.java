/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Samy
 */
public class CategoryDAO {

    private Connection con;

    public CategoryDAO() {
        con = SingletonConnection.getConnection();
    }

    public Category getCategory(int id) throws DAOException {
        String req = "select * from category where id = ?";
        Category cat = null;

        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet resultat = ps.executeQuery()) {
                if (resultat.next()) {
                    cat = new Category(resultat.getInt(1), resultat.getString(2));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cant get Category" + e.getMessage());
        }
        return cat;

    }
}
