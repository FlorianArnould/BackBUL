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
public class Activity {

    private int idA;
    private  String title ;
    private  String description ;
    private   String name ;
    private Category category;
    private String phone ;
    private String email ;

    

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }
    
    
    
    public Activity(int id ,String title, String description, String name) {
        this.idA=id;
        this.title = title;
        this.description = description;
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      
   
}
