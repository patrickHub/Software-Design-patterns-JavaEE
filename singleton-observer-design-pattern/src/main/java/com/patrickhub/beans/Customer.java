/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.patrickhub.constraints.ValidDateFormat;


/**
 *
 * @author PatrickHub
 */


public class Customer {
    
    private int userID;
    @NotNull(message = "This field is required!")
    @Size(min = 5, max = 10, message = "This field must be 5 caracters min and 10 caracters max")
    @Pattern(regexp = ".*[a-zA-Z]+.*", message="This field must contain a least one caracter")
    private String username;
    
    @NotNull(message = "This field is required!")
    @Size(min = 5, max = 10, message = "This field must be 5 caracters min and 10 caracters max")
    private String password;
    
    @NotNull(message = "This field is required!")
    @Size(max = 30, message = "This field must be 30 caracters max")
    private String firstName;
    
    @NotNull(message = "This field is required!")
    @Size(max = 30, message = "This field must be 30 caracters max")
    private String lastName;
    
    @NotNull(message = "This field is required!")
    @Size(max = 30, message = "This field must be 30 caracters max")
    @Email(message = "This field must be valid email")
    private String email;
    
    @NotNull(message = "This field is required!")
    @Size(min=10, max = 10, message = "This field must be 10 caracters")
    @Pattern(regexp = "^[0-9]*$", message = "This field must contains only numbers")
    private String phone;
    
    @NotNull(message="This  field is required!")
    @ValidDateFormat(pattern = "yyyy-MM-dd", message = "This date is invalid")
    private String birthdate;
    
    
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the id to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    
}
