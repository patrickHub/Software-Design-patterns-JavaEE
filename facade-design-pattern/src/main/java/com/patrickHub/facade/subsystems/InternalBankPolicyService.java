/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickHub.facade.subsystems;

import com.patrickHub.facade.bean.Customer;

/**
 *
 * @author PatrickHub
 */
public class InternalBankPolicyService {
     public boolean checkCompliance(Customer customer) {
        // Check that customer conforms to bank policy on lending
        return true;
    }
}
