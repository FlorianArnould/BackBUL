/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.model;

/**
 * @author Samy
 */
public class Category {

    private int idC;
    private String name;

    public Category(int idC, String name) {
        this.idC = idC;
        this.name = name;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id :" +idC + "Name :" +name;
    }
    
}
