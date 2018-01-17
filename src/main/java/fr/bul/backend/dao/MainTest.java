/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import fr.bul.backend.model.Activity;
import fr.bul.backend.model.Post;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Samy
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println("hello world");
        ActivityDAO DAOAc = new ActivityDAO();
        CategoryDAO DAOCa = new CategoryDAO();

        PostDAO DAOPo = new PostDAO();

        ArrayList<Activity> ac = new ArrayList<Activity>();

        ArrayList<Post> posts = new ArrayList<Post>();


        try {
            System.out.println(DAOCa.getCategory(1));
            ac = DAOAc.getActivities("", "shop");
            for (Activity act : ac) {
                System.out.println(act.getCategory());
                JSONObject json = new JSONObject();
                json = act.toJSON();
                System.out.println(json);
            }
//                
            //}

//        posts = DAOPo.getPosts();
//        System.out.println("size :" +posts.size());
//            for (Post p : posts) {
//                JSONObject json = new JSONObject();
//		json = p.toJSON();
//                System.out.println(json);
//            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

}
