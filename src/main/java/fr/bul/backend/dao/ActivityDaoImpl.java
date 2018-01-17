/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.Activity;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author Samy
 */
public class ActivityDaoImpl implements ActivityDAO {
    private Connection con;

    public ActivityDaoImpl() {
        con = SingletonConnection.getConnection();
        System.out.println("connexion réussie ");
    }


    @Override
    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> activities = null;
//         
//         String req = "select * from category ";
//         ArrayList<Activity> listeT = new ArrayList<Activity>();
//         try {
//        PreparedStatement ps = con.prepareStatement(req);
//        ResultSet resultat = ps.executeQuery();
//        while(resultat.next()){
//            Tournoi t  = new Tournoi();
//            t.setCodeT(resultat.getInt(1));
//            t.setNom(resultat.getString(2));
//            t.setDate(resultat.getString(3));
//            t.setLieu(resultat.getString(4));
//            listeT.add(t);
//        }
//       }catch(Exception e ) {
//            e.printStackTrace();
//        }
        return activities;
    }


}
