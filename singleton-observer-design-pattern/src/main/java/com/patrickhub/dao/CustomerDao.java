/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.dao;

import com.patrickhub.beans.Customer;
import com.patrickhub.events.CustomerEvent;
import com.patrickhub.utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class CustomerDao {
    
    @Inject
    private DBConnection dbConnection;
    @Inject
    private Event<Customer> customerEvents;
    
    public void saveCustomer(@Observes @CustomerEvent(CustomerEvent.Type.USERADD) Customer customer){
       
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql insert
        String sql = "INSERT INTO customers(customerFirstName, customerLastName, "
                        + "customerEmail, customerPhone, customerBirthdate, userID) "
                        + "VALUES(?,?,?,?,?,?);";

        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql insert parameters
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setDate(5, new Date(Utils.formatDate(customer.getBirthdate()).getTime()));
            statement.setInt(6, customer.getUserID());
            // execute sql insert
            int row = statement.executeUpdate();
            if (row == 0) {
                throw new SQLException("Failled to create Customer, no rows affected.");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateCustomer(@Observes @CustomerEvent(CustomerEvent.Type.UPDATE) @Priority(10) Customer customer){
       
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql update
        String sql = "UPDATE customers SET customerFirstName = ?, customerLastName = ?, "
                        + "customerEmail = ?, customerPhone = ?, customerBirthdate = ? "
                        + "WHERE userID = ?;";

        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql insert parameters
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setDate(5, new Date(Utils.formatDate(customer.getBirthdate()).getTime()));
            statement.setInt(6, customer.getUserID());
            // execute sql insert
            statement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * delete a customer.
     * 
     * @param id 
     */
    public void deleteCustomer(@Observes @Priority(10) Integer id){
        
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql delete query
        String sql = "DELETE FROM customers  WHERE userID = ?;";
        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql delete parameters
            statement.setInt(1, id);
            // execute sql delete
            int row = statement.executeUpdate();
            // check if user entity is created
            if (row == 0) {
                throw new SQLException("Failled to delete customer, no rows affected.");
	    }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
