package com.meritamerica.assignment6.models;

import javax.persistence.*;

@Entity
public class AccountHolderContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "birth_date")
    private String birthDate;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_holder_id", referencedColumnName = "account_holder_id")
//    private AccountHolder accountHolder;

    public AccountHolderContactDetails() { super(); }

    public AccountHolderContactDetails(AccountHolder accountHolder) {
//        this.accountHolder = accountHolder;
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

    public String getBirthDate() { return this.birthDate; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

}
