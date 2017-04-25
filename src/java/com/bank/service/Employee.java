package com.bank.service;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String position;
    private String username;
    private String password;

    // get employee id
    public int getEmployeeId() {
        return employeeId;
    }

    // set employee id
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // get name
    public String getEmployeeName() {
        return employeeName;
    }

    // set name
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    // get position
    public String getPosition() {
        return position;
    }

    // set position
    public void setPosition(String position) {
        this.position = position;
    }

    // get username
    public String getUsername() {
        return username;
    }

    // set username
    public void setUsername(String username) {
        this.username = username;
    }

    // get password
    public String getPassword() {
        return password;
    }

    // set password
    public void setPassword(String password) {
        this.password = password;
    }
}
