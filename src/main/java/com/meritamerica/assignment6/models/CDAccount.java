package com.meritamerica.assignment6.models;

import com.meritamerica.assignment6.exceptions.TermNotReachedException;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class represents cd accounts of account holders at Merit Bank and contains a cd
 * offering which consists of a term and interest rate that is associated with this
 * account.
 */
@Entity
public class CDAccount extends BankAccount {

    /** the primary key for CD Accounts */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** the cd offering associated with this account */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_offering_id")
    protected CDOffering cdOffering;

    /** the term in years( associated with this account */
    @Min(value = 1, message = "The minimum term of a cd offering is 1 year.")
    protected int term;

    public CDAccount() {
        super(MeritBank.getNextAccountNumber(), 0, new Date());
    }

    public CDAccount(CDOffering cdOffering, double balance) {
        super(balance, cdOffering.getInterestRate(), new Date());
        this.cdOffering = cdOffering;
    }

    private CDAccount(long accountNumber, double balance, double interestRate, int term, Date openedOn) {
        super(accountNumber, balance, interestRate, openedOn);
        this.cdOffering = new CDOffering(term, interestRate);
        this.term = term;
    }

    public long getId() { return this.id; }

    public void setId(long id) { this.id = id; }

    public int getTerm() { return this.cdOffering.getTerm(); }

    public CDOffering getCdOffering() { return this.cdOffering; }

    public static CDAccount readFromString(String accountData) throws ParseException {
        String[] temp = accountData.split(",");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long accountNumber = Long.parseLong(temp[0]);
        double balance = Double.parseDouble(temp[1]);
        double interestRate = Double.parseDouble(temp[2]);
        Date date = dateFormat.parse(temp[3]);
        int term = Integer.parseInt(temp[4]);

        return new CDAccount(accountNumber, balance, interestRate, term, date);
    }

    public double futureValue(){ return super.futureValue(this.cdOffering.getTerm()); }

    @Override
    public String writeToString() { return super.writeToString() + "," + this.cdOffering.getTerm(); }

    /**
     * this method returns true and deposits the requested amount into this cd account if the account
     * has reached the end of its term.
     *
     * @param amount the amount to be deposited
     * @return a boolean determining if the transaction was successful or not
     */
    @Override
    public boolean deposit(double amount) {
        Date currentDate = new Date();
        int currentTermYear = openedOn.getYear() - currentDate.getYear();
        if (currentTermYear > term && amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    /**
     * this method withdraws the requested amount from this cd account if the account
     * has reached maturity
     *
     * @param amount the amount to be withdrawn
     * @return a boolean determining if the transaction was successful or not
     */
    @Override
    public boolean withdraw(double amount) throws TermNotReachedException {
        Date currentDate = new Date();
        int currentTermYear = openedOn.getYear() - currentDate.getYear();
        if (!(currentTermYear > term && amount <= this.getBalance() && amount > 0)) {
            throw new TermNotReachedException("This CD Account has not reached maturity.");
        } else {
            balance -= amount;
            return true;
        }
    }


}