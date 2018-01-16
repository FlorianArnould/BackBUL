/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Samy
 */
public class SingletonConnection {
    private static Connection  connection ;
    static {
        try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/bulbase";
                String user = "postgres";
                String passwd = "jack1010";
                connection = DriverManager.getConnection(url, user, passwd);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
    
}
