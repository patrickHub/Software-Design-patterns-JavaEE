/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickHub.facade.subsystems;

/**
 *
 * @author PatrickHub
 */
public class RepaymentPayabilityService {
    public boolean calculatePayability(Double principle, Double income, Integer months) {
        // Calculate payability based on bank policy
        // If monthly repayment is less than 50% of monthly income then repayment in affordable
        return (income / 12) * 0.5 > principle / months;
    }
}
