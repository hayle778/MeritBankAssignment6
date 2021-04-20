package com.meritamerica.assignment6.models;

public class CDOffering {

    protected int id;
    protected double interestRate;
    protected int term;
    private static int nextId = 1;

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