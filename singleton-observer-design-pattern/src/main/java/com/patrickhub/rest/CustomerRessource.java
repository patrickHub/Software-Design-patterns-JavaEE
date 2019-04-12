/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.rest;

import com.patrickhub.beans.Customer;
import com.patrickhub.events.CustomerEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PatrickHub
 */

@Path("customer")
public class CustomerRessource {
    
    @Inject @CustomerEvent(CustomerEvent.Type.ADD)
    private Event<Customer> eventAddCustomer;
    
    @Inject @CustomerEvent(CustomerEvent.Type.UPDATE)
    private Event<Customer> eventUpdateCustomer;
    
    @Inject 
    private Event<Integer> eventDeleteCustomer;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(@Valid Customer customer){
       eventAddCustomer.fire(customer);
       return Response.status(200).entity("Customer is created successfully!").build();
    }
    
    @PUT
    @Path("{id: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateCustomer(@PathParam("id") int id, @Valid Customer customer){
        customer.setUserID(id);
        eventUpdateCustomer.fire(customer);
        return Response.status(200).entity("Customer: " + customer.getUserID()+ " is updated successfully!").build();
    }
    
    @DELETE
    @Path("{id : \\d+}") // to accepte digit only
    public Response deleteCustomer(@PathParam("id") int id){
        eventDeleteCustomer.fire(id);
        return Response.status(200).entity("Customer: " + id + " is deleted!").build();
    }
    
}
