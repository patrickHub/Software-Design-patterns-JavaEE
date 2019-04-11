/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.rest;

import com.patrickhub.beans.Customer;
import com.patrickhub.events.CustomerEvent;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
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
    
    @Inject @CustomerEvent(CustomerEvent.Type.ADD)
    private Event<Customer> eventAddCustomer;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(@Valid Customer customer){
       eventAddCustomer.fire(customer);
    }
    
}
