package com.bank.service;

public class Account {

    private int accountId;
    private String accountNo;
    private String accountType;
    private double accountBalance;
    private String sortCode;
    private String cardNumber;
    private Customer customer;

    // get account id
    public int getAccountId() {
        return accountId;
    }

    // set account id
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    // get account no
    public String getAccountNo() {
        return accountNo;
    }

    // set account no
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    // get account type
    public String getAccountType() {
        return accountType;
    }

    // set account type
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // get balance
    public double getAccountBalance() {
        return accountBalance;
    }

    // set balance
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    // get code
    public String getSortCode() {
        return sortCode;
    }

    // set code
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    // get card no
    public String getCardNumber() {
        return cardNumber;
    }

    // set card no
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

     // get customer
    public Customer getCustomer() {
        return customer;
    }

    // set customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
