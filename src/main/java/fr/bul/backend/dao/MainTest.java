/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

import java.sql.Connection;
import java.util.ArrayList;
import javafx.beans.binding.ListExpression;
import model.Post;

/**
 *
 * @author Samy
 */
public class MainTest {
    public static void main(String[] args) {
//        Connection con ; 
//        con  = SingletonConnection.getConnection();
    PostDAO pdao = new PostDAO();
    ArrayList<Post> listP = new ArrayList<Post>();
    
    System.out.println(listP.size());
    Post post = new Post("vente maison", "je vends voiture", "Antoine");
    try {
        listP =pdao.getPosts("bonjour");
        pdao.createPost(post);
    }catch(DAOException e){
        e.printStackTrace();
    }}
}
