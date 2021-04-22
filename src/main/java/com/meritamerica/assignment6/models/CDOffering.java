package com.meritamerica.assignment6.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * this class represents a cd offering which consist of an id, term and interest rate
 */
public class CDOffering {

    private static int nextId = 1;

    /** the id of an individual cd offering */
    protected long id;
    /** the interest rate of the offering */
    @Positive(message = "A cd offering must contain a positive interest rate (greater than 0)")
    @Max(value = 1, message = "The interest rate must be a percentage (less than 1)")
    protected double interestRate;
    /** the term of the offering */
    @Min(value = 1, message = "The minimum term of a cd offering is 1 year.")
    protected int term;



    public CDOffering() {
        this.id = getNextId();
    }

    public CDOffering(int term, double interestRate){
        this.id = nextId++;
        this.interestRate = interestRate;
        this.term = term;
    }

    public int getTerm() { return this.term; }

    public double getInterestRate() { return this.interestRate; }

    public long getId() { return this.id; }

    private static long getNextId() { return nextId++; }

    static CDOffering readFromString(String cdOfferingDataString){
        String[] temp = cdOfferingDataString.split(",");
        return new CDOffering(Integer.parseInt(temp[0]), Double.parseDouble(temp[1]));
    }

    public String writeToString() { return this.term + "," + this.interestRate; }
}