
package com.foodmp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("User_id")
    @Expose
    private Integer userid;
    @SerializedName("Name")
    @Expose
    private String firstname;

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Phone_num")
    @Expose
    private String contact;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("User_type")
    @Expose
    private String type;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
