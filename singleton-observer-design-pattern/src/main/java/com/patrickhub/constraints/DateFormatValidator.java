/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author PatrickHub
 */
public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String>{

    private String pattern;
    
    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
       this.pattern = constraintAnnotation.pattern();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }
        try{
            Logger.getLogger(DateFormatValidator.class.getName()).info("Valid date");
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
            Date date =  format.parse(value);

            if(!date.before(new Date(System.currentTimeMillis()))) {
                    return false;
            }
            return true;
        } catch (ParseException e) {
                 Logger.getLogger(DateFormatValidator.class.getName()).info("Invalid date");
                return false;
        }
    }
    
    
    /* Check if a string date is valid.
    * 
    * @param str string date
    * @return boolean
    */
   private boolean isValidDate(String str) {
           boolean isValid = true;
           try {
               Logger.getLogger(DateFormatValidator.class.getName()).info("Valid date");
                String patterns = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(patterns, Locale.ENGLISH);
                Date date =  format.parse(str);
                
                if(!date.before(new Date(System.currentTimeMillis()))) {
                        isValid = false;
                }

            } catch (ParseException e) {
                     Logger.getLogger(DateFormatValidator.class.getName()).info("Invalid date");
                    isValid = false;
            }
           return isValid;
   }
    
}
