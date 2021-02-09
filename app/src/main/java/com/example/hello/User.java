package com.example.hello;

public class User {
    public String profile;
    public String id;
    private String pw;
    private String UserName;

    public User(){}

    public String getProfile(){
        return profile;
    }

    public void setProfile(String profile){
        this.profile = profile;
    }

    public String getid(){
        return id;
    }

    public void setid(String id){
        this.id = id;
    }

    public String getPw(){
        return pw;
    }

    public void setPw(String pw){
        this.pw = pw;
    }


}
