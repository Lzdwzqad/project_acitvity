package com.activity.model;

public class User {
    private Integer userno;

    private String uname;

    private String password;

    private String phone;

    private String identity;

    private String payAccoutnt;

    private Integer creditNumber;

    private Double balance;

    private Integer state;

    public User(Integer userno, String uname, String password, String phone, String identity, String payAccoutnt, Integer creditNumber, Double balance, Integer state) {
        this.userno = userno;
        this.uname = uname;
        this.password = password;
        this.phone = phone;
        this.identity = identity;
        this.payAccoutnt = payAccoutnt;
        this.creditNumber = creditNumber;
        this.balance = balance;
        this.state = state;
    }

    public User() {
        super();
    }

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPayAccoutnt() {
        return payAccoutnt;
    }

    public void setPayAccoutnt(String payAccoutnt) {
        this.payAccoutnt = payAccoutnt;
    }

    public Integer getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(Integer creditNumber) {
        this.creditNumber = creditNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}