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

    private  String title ;
    private  String description;
    private  String name ;
    private  String tel;
    private  String email;

    public Post(String title, String description, String Name) {
        this.title = title;
        this.description = description;
        this.name = Name;
        this.tel="";
        this.email="";
    }
    
    public Post(String title, String description, String Name,String tel) {
        this.title = title;
        this.description = description;
        this.name = Name;
        this.tel=tel;
        this.email="";
    }
    public Post(String title, String description, String Name,String tel , String email) {
        this.title = title;
        this.description = description;
        this.name = Name;
        this.tel = tel;
        this.email = email; 
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     @Override
    public String toString() {
        return "Titre : "+title+"Description"+description+"Name : "+name+"tel"+tel+"email :"+email+"\n"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
