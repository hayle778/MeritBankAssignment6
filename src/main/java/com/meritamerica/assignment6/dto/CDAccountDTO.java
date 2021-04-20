package com.meritamerica.assignment6.dto;

import com.meritamerica.assignment6.models.CDOffering;

public class CDAccountDTO{

    private double balance;
    private CDOffering cdOffering;

    public double getBalance() { return balance; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CDOffering getCDOffering() { return cdOffering; }

    public void setCDOffering(CDOffering cdOffering) {
        this.cdOffering = cdOffering;
    }
}
