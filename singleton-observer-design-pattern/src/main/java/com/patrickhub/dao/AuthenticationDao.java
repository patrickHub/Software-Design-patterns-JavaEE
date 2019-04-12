/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.dao;

import com.patrickhub.beans.Customer;
import com.patrickhub.events.CustomerEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author PatrickHub
 */
public class AuthenticationDao {
    
    @Inject
    private DBConnection dbConnection;
    @Inject @CustomerEvent(CustomerEvent.Type.USERADD)
    private Event<Customer> eventAddUser;
    
    
    /**
     * create a user credentials after observe
     * the creation of a new customer.
     * 
     * @param customer 
     */
    public void createUserCredentials(@Observes @CustomerEvent(CustomerEvent.Type.ADD) Customer customer){
        boolean isInsert = false;
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql insert
        String sql = "INSERT INTO users(username, password) VALUES(?,?);";
        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // set sql insert parameters
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            // execute sql insert
            int row = statement.executeUpdate();
            // check if user entity is created
            if (row == 0) {
                throw new SQLException("Failled to create user, no rows affected.");
	    }
			
            // check weather userID has been generated
	    ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
                customer.setUserID(generatedIds.getInt(1));
                eventAddUser.fire(customer);
            }
            else {
                throw new SQLException("Failled to create user, no userID obtained.");
            }
	        
           
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * update user credentials.
     * 
     * @param customer 
     */
    public void updateUserCredentials(@Observes @CustomerEvent(CustomerEvent.Type.UPDATE) @Priority(20) Customer customer){
 
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql insert
        String sql = "UPDATE users SET username=?, password=? WHERE userID=? ;";
        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // set sql update parameters
            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            statement.setInt(3, customer.getUserID());
            // execute sql update
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * delete a user credentials.
     * 
     * @param id of the user to delete
     */
    public void deleteUserCredentials(@Observes @Priority(20) Integer id){
   
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql delete query
        String sql = "DELETE FROM users  WHERE userID = ?;";
        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql delete parameters
            statement.setInt(1, id);
            // execute sql delete
            int row = statement.executeUpdate();
            // check if user entity is created
            if (row == 0) {
                throw new SQLException("Failled to delete user, no rows affected.");
	    }
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
