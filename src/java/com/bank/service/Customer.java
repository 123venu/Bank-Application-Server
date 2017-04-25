package com.bank.service;

import java.util.ArrayList;

public class Customer {

    private int customerId;
    private String dob;
    private String customerName;
    private String address;
    private String email;
    private String mobile;
    private ArrayList<Account> accounts= new ArrayList<>();

    // get customer id
    public int getCustomerId() {
        return customerId;
    }

    // set customer id
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // get dob
    public String getDob() {
        return dob;
    }

    // set dob
    public void setDob(String dob) {
        this.dob = dob;
    }

    // get address
    public String getAddress() {
        return address;
    }

    // set address
    public void setAddress(String address) {
        this.address = address;
    }

    // get email
    public String getEmail() {
        return email;
    }

    // set email
    public void setEmail(String email) {
        this.email = email;
    }

    // get name
    public String getCustomerName() {
        return customerName;
    }

    // set name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // get account
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // set account
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    // get mobile
    public String getMobile() {
        return mobile;
    }

    // set mobile
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
