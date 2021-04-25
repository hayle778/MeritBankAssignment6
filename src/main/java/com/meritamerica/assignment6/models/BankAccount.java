package com.meritamerica.assignment6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment6.exceptions.TermNotReachedException;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * this abstract class represents the basic object and values of a bank account
 */
@MappedSuperclass
public abstract class BankAccount {

    /** the account holder associated with any number of bank accounts */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_holder_id")
    @JsonIgnore
    private AccountHolder accountHolder;

    /** the date this account was created on */
    protected Date openedOn;
    /** the account number associated with this bank account */
    protected Long accountNumber;
    /** the interest rate of this bank account */
    protected double interestRate;
    /** the balance of this bank account */
    protected double balance;

    /** a list of the transactions associated with this bank account */
    @JsonIgnore
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    // endregion

    // region constructors

    public BankAccount() {
    }

    public BankAccount(double balance, double interestRate){
        this(MeritBank.getNextAccountNumber(), balance, interestRate, new Date());
    }

    public BankAccount(double balance, double interestRate, Date openedOn){
        this(MeritBank.getNextAccountNumber(), balance, interestRate, openedOn);
    }

    protected BankAccount(long accountNumber, double balance, double interestRate, Date openedOn){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.openedOn = openedOn;
    }
    // endregion

    // region getters/setters
    public long getAccountNumber() { return this.accountNumber; }

    public double getBalance() { return this.balance; }

    public double getInterestRate() { return this.interestRate; }

    public Date getOpenedOn() { return this.openedOn; }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    /**
     * this method withdraws the requested amount from this bank account if the accounts
     * balance is greater than the withdrawal amount
     *
     * @param amount the amount to be withdrawn
     * @return a boolean determining if the transaction was successful or not
     */
    public boolean withdraw(double amount) throws TermNotReachedException {
        if (this.balance - amount < 0) {
            return false;
        } else {
            this.balance -= amount;
            return true;
        }
    }

    /**
     * this method deposits the requested amount into this bank account if the request is for
     * a positive amount
     *
     * @param amount the amount to be deposited
     * @return a boolean determining if the transaction was successful or not
     */
    public boolean deposit (double amount) {
        if (amount < 0) {
            return false;
        } else {
            this.balance += amount;
            return true;
        }
    }

    /**
     * this method adds a transaction that has been completed or requested on this bank
     * account
     *
     * @param transaction the transaction being added to this account
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * this method returns all transactions associated with this account
     *
     * @return all the transactions associated with this account
     */
    public List<Transaction> getTransactions() { return transactions; }
    // endregion

    // region read/write from string
    static BankAccount readFromString(String accountData) throws ParseException { return null; }

    public String writeToString() {
        return this.accountNumber + "," + this.balance + "," + String.format("%.4f", this.interestRate) + "," +
                (this.openedOn.getDate() + 1) + "/" +
                this.openedOn.getMonth() + "/" +
                (this.openedOn.getYear() + 1900);
    }
    // endregion

    /**
     * the future value of this account based on the amount of years passed in
     *
     * @param years the amount of years you want to calculate the future value for
     * @return the future value of this account in x years
     */
    public double futureValue(int years) { return MeritBank.recursiveFutureValue(this.balance, this.interestRate, years); }
}
