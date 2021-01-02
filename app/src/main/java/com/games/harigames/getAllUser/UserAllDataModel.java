package com.games.harigames.getAllUser;

public class UserAllDataModel {
    String name;
    String role;
    String status;
    String credit_ref;
    String downLine;
    String balance;


    String exposer_limit;
    String userName;

    String created_by;
    String mobile;
    String password;
    String upLine;


    public String getCredit_ref() {
        return credit_ref;
    }

    public void setCredit_ref(String credit_ref) {
        this.credit_ref = credit_ref;
    }

    public String getDownLine() {
        return downLine;
    }

    public void setDownLine(String downLine) {
        this.downLine = downLine;
    }

    public String getExposer_limit() {
        return exposer_limit;
    }

    public void setExposer_limit(String exposer_limit) {
        this.exposer_limit = exposer_limit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpLine() {
        return upLine;
    }

    public void setUpLine(String upLine) {
        this.upLine = upLine;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    public UserAllDataModel(){

    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public UserAllDataModel(String balance, String upLine, String password, String mobile, String downLine, String created_by, String name, String role, String status, String credit_ref, String exposer_limit, String username) {
        this.name = name;
        this.created_by=created_by;
        this.role = role;
        this.status = status;
        this.credit_ref = credit_ref;
        this.exposer_limit=exposer_limit;
        this.downLine=downLine;
        this.mobile=mobile;
        this.password=password;
        this.upLine=upLine;
        this.balance= balance;


        this.userName=username;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCredit() {
        return credit_ref;
    }

    public void setCredit(String credit) {
        this.credit_ref = credit;
    }




    public String getExposer() {
        return exposer_limit;
    }

    public void setExposer(String exposer) {
        this.exposer_limit = exposer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
}


