/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.rest;

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
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCustomer(){
        return  "";
    }
    
}
