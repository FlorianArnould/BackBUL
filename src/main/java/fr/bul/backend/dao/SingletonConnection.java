/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;


public class SingletonConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonConnection.class);
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://127.0.0.1:5432/bulbase";
            String user = "postgres";
            String password = "jack1010";
            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            LOGGER.error("Cannot open the database : " + e.getMessage(), e);
        }
    }

    private SingletonConnection() {

    }

    public static Connection getConnection() {
        return connection;
    }

}
