package com.meritamerica.assignment6.dto;

import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.models.MeritBank;

/**
 * this is a wrapper class for cd accounts which has been primarily created to facilitate
 * the transfer of data of cd accounts through the API
 */
public class CDAccountDTO{

    /** the id of the cd offering */
    private long id;
    /** the balance of the cd account */
    private double balance;
    /** the cd offering associated with the cd account */
    private CDOffering cdOffering;

    public CDAccountDTO() {}

    public CDAccountDTO(double balance, long id) {
        this.balance = balance;
        this.id = id;
        this.cdOffering = MeritBank.getCDOfferingById(id);
    }

    public double getBalance() { return this.balance; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CDOffering getCDOffering() { return this.cdOffering; }

    public void setCDOffering(CDOffering cdOffering) {
        this.cdOffering = cdOffering;
    }
}
