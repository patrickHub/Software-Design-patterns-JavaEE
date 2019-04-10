/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author PatrickHub
 */

@Path("greetUser")
public class GreetRessource {
    
    @GET
    public String greetUser(){
        return "Singleton and Observer Design Patterns in Java EE starts!";
    }
}
