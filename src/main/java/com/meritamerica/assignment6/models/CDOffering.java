package com.meritamerica.assignment6.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * this class represents a cd offering which consist of an id, term and interest rate
 */
@Entity
public class CDOffering {

    /** the id of an individual cd offering */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    /** the interest rate of the offering */
    @Positive(message = "A cd offering must contain a positive interest rate (greater than 0)")
    @Max(value = 1, message = "The interest rate must be a percentage (less than 1)")
    protected double interestRate;
    /** the term of the offering */
    @Min(value = 1, message = "The minimum term of a cd offering is 1 year.")
    protected int term;



    public CDOffering() {}

    public CDOffering(int term, double interestRate){
        this.interestRate = interestRate;
        this.term = term;
    }

    public int getTerm() { return this.term; }

    public double getInterestRate() { return this.interestRate; }

    public long getId() { return this.id; }

    public void setId(long id) { this.id = id; }

    static CDOffering readFromString(String cdOfferingDataString){
        String[] temp = cdOfferingDataString.split(",");
        return new CDOffering(Integer.parseInt(temp[0]), Double.parseDouble(temp[1]));
    }

    public String writeToString() { return this.term + "," + this.interestRate; }
}