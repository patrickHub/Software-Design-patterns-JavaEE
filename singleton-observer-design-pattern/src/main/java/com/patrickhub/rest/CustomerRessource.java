/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.rest;

import com.patrickhub.beans.Customer;
import com.patrickhub.dao.UserDao;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author PatrickHub
 */

@Path("customer")
public class CustomerRessource {
    
    @Inject
    private UserDao userDao;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCustomer(@Valid Customer customer){
        if(userDao.saveCustomer(customer)){
            return  "Customer created successfully!";
        }
        return "Fail to create Customer!";
    }
    
}
