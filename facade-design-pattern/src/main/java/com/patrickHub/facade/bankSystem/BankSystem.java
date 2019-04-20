/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickHub.facade.bankSystem;

import com.patrickHub.facade.bean.Customer;
import com.patrickHub.facade.subsystems.CreditRatingService;
import com.patrickHub.facade.subsystems.InternalBankPolicyService;
import com.patrickHub.facade.subsystems.RepaymentPayabilityService;
import com.patrickHub.facade.subsystems.RepaymentService;
import com.patrickHub.facade.subsystems.TransferService;
import javax.ejb.EJB;

/**
 *
 * @author Patrick-PC
 */
public class BankSystem {
    @EJB
    private CreditRatingService creditRatingService;

    @EJB
    private RepaymentPayabilityService payabilityService;

    @EJB
    private InternalBankPolicyService internalBankPolicyService;

    @EJB
    private TransferService transferService;

    @EJB
    private RepaymentService repaymentService;

    public boolean processLoanApplication(Customer customer, Double principle, Double income, Integer months) {

        boolean passes = creditRatingService.checkCustomerRating(customer);
        if (!passes) return false;

        boolean affordable = payabilityService.calculatePayability(principle, income, months);
        if (!affordable) return false;

        boolean compliant = internalBankPolicyService.checkCompliance(customer);
        if (!compliant) return false;

        // Application successful, so transfer principle and set up repayment schedule
        transferService.makeTransfer(principle, customer);
        repaymentService.setUpPaymentSchedule(customer, principle, months);

        return true;
    }
}
