package com.sabroso.qms.Models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*@Entity(tableName = "CustomerDetail")*/
public class CustomerDetail implements Serializable
{
   /*@PrimaryKey(autoGenerate = true)
   @SerializedName("id")
    @Expose
    private int id;*/

    @SerializedName("Customer_Id")
    @Expose
    private String customerId;
    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("Customer_Address")
    @Expose
    private String customerAddress;
    @SerializedName("Customer_Lat")
    @Expose
    private String customerLat;
    @SerializedName("Customer_Long")
    @Expose
    private String customerLong;
    @SerializedName("User_Id")
    @Expose
    private String userId;
    @SerializedName("Customer_AssetID")
    @Expose
    private String customerAssetID;
    @SerializedName("Last_Visit_Date")
    @Expose
    private String lastVisitDate;
    @SerializedName("Last_Visit_By")
    @Expose
    private String lastVisitBy;
    @SerializedName("Next_Visit_Date")
    @Expose
    private String nextVisitDate;
    @SerializedName("Visit_Interval")
    @Expose
    private Integer visitInterval;
    @SerializedName("ZASA_Users")
    @Expose
    private Object zASAUsers;
    private final static long serialVersionUID = -5652913514972166086L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CustomerDetail() {
    }

    /**
     *
     * @param customerAddress
     * @param customerAssetID
     * @param customerLat
     * @param nextVisitDate
     * @param customerId
     * @param visitInterval
     * @param customerLong
     * @param lastVisitDate
     * @param lastVisitBy
     * @param userId
     * @param customerName
     * @param zASAUsers
     */
    public CustomerDetail(String customerId, String customerName, String customerAddress, String customerLat, String customerLong, String userId, String customerAssetID, String lastVisitDate, String lastVisitBy, String nextVisitDate, Integer visitInterval, Object zASAUsers) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerLat = customerLat;
        this.customerLong = customerLong;
        this.userId = userId;
        this.customerAssetID = customerAssetID;
        this.lastVisitDate = lastVisitDate;
        this.lastVisitBy = lastVisitBy;
        this.nextVisitDate = nextVisitDate;
        this.visitInterval = visitInterval;
        this.zASAUsers = zASAUsers;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerLat() {
        return customerLat;
    }

    public void setCustomerLat(String customerLat) {
        this.customerLat = customerLat;
    }

    public String getCustomerLong() {
        return customerLong;
    }

    public void setCustomerLong(String customerLong) {
        this.customerLong = customerLong;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerAssetID() {
        return customerAssetID;
    }

    public void setCustomerAssetID(String customerAssetID) {
        this.customerAssetID = customerAssetID;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getLastVisitBy() {
        return lastVisitBy;
    }

    public void setLastVisitBy(String lastVisitBy) {
        this.lastVisitBy = lastVisitBy;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public Integer getVisitInterval() {
        return visitInterval;
    }

    public void setVisitInterval(Integer visitInterval) {
        this.visitInterval = visitInterval;
    }

    public Object getZASAUsers() {
        return zASAUsers;
    }

    public void setZASAUsers(Object zASAUsers) {
        this.zASAUsers = zASAUsers;
    }

}