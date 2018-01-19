/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.GPSCoordinates;
import fr.bul.backend.model.Stat;
import java.util.ArrayList;

/**
 *
 * @author Samy
 */
public class MainTest {
    public static void main(String args[]){
        StatsDao daos = new StatsDao();
        
        ArrayList<Stat> stats = new ArrayList<Stat>();
        Stat test = new Stat("actualites", new GPSCoordinates(45, 0.1), 02222);
        try {
        daos.addStat(test);
        stats=(ArrayList)daos.getStats();
            System.out.println("la taille est : "+stats.size());
            for (Stat stat : stats) {
			System.out.println(stat.getService());
		}
        }catch(DAOException e) {
            e.printStackTrace();
        }
    
    }
    
}
