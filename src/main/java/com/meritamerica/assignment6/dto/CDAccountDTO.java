package com.meritamerica.assignment6.dto;

import com.meritamerica.assignment6.models.CDOffering;

public class CDAccountDTO{

    private double balance;
    private CDOffering cdOffering;

    public CDAccountDTO() {}

    public CDAccountDTO(double balance, CDOffering cdOffering) {
        this.balance = balance;
        this.cdOffering = cdOffering;
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
