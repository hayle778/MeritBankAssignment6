package com.meritamerica.assignment6.models;

public class AccountHolderContactDetails {


    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;

    private AccountHolder accountHolder;

    public AccountHolderContactDetails() {}

    public AccountHolderContactDetails(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
        this.firstName = accountHolder.getFirstName();
        this.middleName = accountHolder.getMiddleName();
        this.lastName = accountHolder.getLastName();
        this.id = accountHolder.getId();
    }

    public long getId() { return this.id; }

    public void setId(long id) { this.id = id; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return this.address; }

    public void setAddress(String address) { this.address = address; }

}
