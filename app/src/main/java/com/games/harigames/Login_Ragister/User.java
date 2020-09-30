package com.games.harigames.Login_Ragister;

public class User {
    public String name;
    public String created_by;
    public String userName;
    public String password;
    public String email;
    public String mobile;
    public String role;
    public String credit_ref;
    public String exposer_limit;
    public String upLine;
    public String downLine;

    public User() {

    }

    public User(String name, String created_by, String userName, String password, String email, String mobile, String role, String credit_ref, String exposer_limit, String upLine, String downLine) {
        this.name = name;
        this.created_by = created_by;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
        this.credit_ref = credit_ref;
        this.exposer_limit = exposer_limit;
        this.upLine = upLine;
        this.downLine = downLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCredit_ref() {
        return credit_ref;
    }

    public void setCredit_ref(String credit_ref) {
        this.credit_ref = credit_ref;
    }

    public String getExposer_limit() {
        return exposer_limit;
    }

    public void setExposer_limit(String exposer_limit) {
        this.exposer_limit = exposer_limit;
    }

    public String getUpLine() {
        return upLine;
    }

    public void setUpLine(String upLine) {
        this.upLine = upLine;
    }

    public String getDownLine() {
        return downLine;
    }

    public void setDownLine(String downLine) {
        this.downLine = downLine;
    }
}
