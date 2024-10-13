package com.example.hack_dearborn_3_project;

import java.io.Serializable;

public class Users implements Serializable
{
    private String uname;
    private String pword;
    private boolean admin;

    //Constructor
    public Users(String uname, String pword, boolean admin) {
        this.uname = uname;
        this.pword = pword;
        this.admin = admin;
    }

    //Getters and Setters
    public String getUname()
    {
        return uname;
    }
    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getPword()
    {
        return pword;
    }
    public void setPword(String pword)
    {
        this.pword = pword;
    }

    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
