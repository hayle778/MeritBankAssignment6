package com.meritamerica.assignment6.models;

/**
 * this class represents a cd offering which consist of an id, term and interest rate
 */
public class CDOffering {

    private static int nextId = 1;

    /** the id of an individual cd offering */
    protected int id;
    /** the interest rate of the offering */
    protected double interestRate;
    /** the term of the offering */
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

    public int getId() { return this.id; }

    private static int getNextId() { return nextId++; }

    static CDOffering readFromString(String cdOfferingDataString){
        String[] temp = cdOfferingDataString.split(",");
        return new CDOffering(Integer.parseInt(temp[0]), Double.parseDouble(temp[1]));
    }

    public String writeToString() { return this.term + "," + this.interestRate; }
}