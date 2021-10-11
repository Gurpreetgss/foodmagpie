
package com.foodmp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOrg {

    @SerializedName("Org_id")
    @Expose
    private Integer orgId;
    @SerializedName("Org_name")
    @Expose
    private String orgName;
    @SerializedName("Org_address")
    @Expose
    private String orgAddress;
    @SerializedName("org_description")
    @Expose
    private String orgDescription;
    @SerializedName("Org_phn")
    @Expose
    private String orgPhn;
    @SerializedName("Org_lat")
    @Expose
    private Double orgLat;
    @SerializedName("Org_long")
    @Expose
    private Double orgLong;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgPhn() {
        return orgPhn;
    }

    public void setOrgPhn(String orgPhn) {
        this.orgPhn = orgPhn;
    }

    public Double getOrgLat() {
        return orgLat;
    }

    public void setOrgLat(Double orgLat) {
        this.orgLat = orgLat;
    }

    public Double getOrgLong() {
        return orgLong;
    }

    public void setOrgLong(Double orgLong) {
        this.orgLong = orgLong;
    }

}
