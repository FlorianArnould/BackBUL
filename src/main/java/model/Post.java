/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Samy
 */
public class Post {

    public Post(int idPost, String title, String description, String Name) {
        this.idPost = idPost;
        this.title = title;
        this.description = description;
        this.Name = Name;
    }
    
    private  int idPost;
    private final String title ;
    private final String description;
    private final String Name ;
    private  String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
    
    
    
}
