package com.meritamerica.assignment6.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class SavingsAccount extends BankAccount {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;



    public SavingsAccount() {
        super(MeritBank.getNextAccountNumber(), 0, MeritBank.SAVINGS_INTEREST_RATE, new Date());
    }

    public SavingsAccount(double openingBalance) {
        super(MeritBank.getNextAccountNumber(), openingBalance, MeritBank.SAVINGS_INTEREST_RATE, new Date());
    }

    private SavingsAccount(long accountNumber, double balance, double interestRate, Date openedOn) {
        super(accountNumber, balance, interestRate, openedOn);
    }

    public long getId() { return this.id; }

    public void setId(long id) { this.id = id; }

    public static SavingsAccount readFromString(String accountData) throws ParseException {
        String[] temp = accountData.split(",");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long accountNumber = Long.parseLong(temp[0]);
        double balance = Double.parseDouble(temp[1]);
        double interestRate = Double.parseDouble(temp[2]);
        Date date = dateFormat.parse(temp[3]);

        return new SavingsAccount(accountNumber, balance, interestRate, date);
    }
}
