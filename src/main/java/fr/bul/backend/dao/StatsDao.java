/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.Post;
import fr.bul.backend.model.Stat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samy
 */
public class StatsDao {
        private Connection con;

    public StatsDao() {
        con = SingletonConnection.getConnection();
    }
    
    public void addStat(Stat stat) throws DAOException {
        String req = "INSERT INTO stats(service,long,lat,date) values (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(req)) {
            ps.setString(1, stat.getService());
            ps.setFloat(2, (float)stat.getCoordinates().getLongitude());
            ps.setFloat(3, (float)stat.getCoordinates().getLatitude());
            ps.setLong(4,stat.getDate());
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("Cannot insert the new post");
            }
        } catch (SQLException e) {
            throw new DAOException("An error occurred while adding new post :" + e.getMessage(), e);
        }
    }
    public List<Stat> getStats() throws DAOException {
        String req = "select * from stats ";
        List<Stat> stats = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(req)) {
            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    GPSCoordinates gps = new GPSCoordinates(result.getFloat("long"), result.getFloat("lat"));
                    Stat stat = new Stat(result.getString("service"),gps , result.getLong("date"));
                    stats.add(stat);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error cannot get Stats  : " + e.getMessage());
        }
        return stats;
    }
}
