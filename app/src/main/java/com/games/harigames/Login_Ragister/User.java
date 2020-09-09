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


}
