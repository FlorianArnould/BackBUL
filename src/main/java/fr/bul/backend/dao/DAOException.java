/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.bul.backend.dao;

/**
 *
 * @author Samy
 */
public class DAOException extends Exception{
    DAOException(String message, Exception e){
        super(message, e);
    }
    
    DAOException(String message){
        super(message);
    }
}
