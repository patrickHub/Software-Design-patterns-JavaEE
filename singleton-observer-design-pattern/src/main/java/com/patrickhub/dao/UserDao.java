/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.dao;

import com.patrickhub.beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author PatrickHub
 */
public class UserDao {
    
    @Inject
    private DBConnection dbConnection;
    
    public boolean saveUser(User user){
        boolean isInsert = false;
        // get db connection
        Connection connection = dbConnection.getConnection();
        // write sql insert
        String sql = "INSERT INTO users(username, password) VALUES(?,?);";
        try {
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql insert parameters
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            // execute sql insert
            int row = statement.executeUpdate();
            if(row == 1){
                isInsert = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isInsert;
    }
}
