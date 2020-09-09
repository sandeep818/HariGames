package com.games.harigames.Login_Ragister;

public class Ragister_Master_Model {
    public String name,userName,password,email,mobile,role,credit_ref,exposer_limit,upLine,downLine,created_by,status;

    public Ragister_Master_Model(){

    }



    public Ragister_Master_Model(String status,String created_by,String name, String userName, String password,  String mobile, String role, String credit_ref, String exposer_limit, String upLine, String downLine) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.created_by=created_by;
        this.status = status;
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

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
