package com.sabroso.qms.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class customers   {


    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Customer_Detail")
    private Object customerDetail;
    @SerializedName("Customer_List")
    private List<CustomerListDTO> customerList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(Object customerDetail) {
        this.customerDetail = customerDetail;
    }

    public List<CustomerListDTO> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerListDTO> customerList) {
        this.customerList = customerList;
    }
    @Entity(tableName = "CustomerList")
    public static class CustomerListDTO {

        @SerializedName("Customer_Id")
        private String customerId;
        @SerializedName("Customer_Name")
        private String customerName;
        @SerializedName("Customer_Address")
        private String customerAddress;
        @SerializedName("Customer_Lat")
        private String customerLat;
        @SerializedName("Customer_Long")
        private String customerLong;
        @SerializedName("User_Id")
        private String userId;
        @SerializedName("Customer_AssetID")
        private String customerAssetid;
        @SerializedName("Last_Visit_Date")
        private String lastVisitDate;
        @SerializedName("Last_Visit_By")
        private String lastVisitBy;
        @SerializedName("Next_Visit_Date")
        private String nextVisitDate;
        @SerializedName("Visit_Interval")
        private Integer visitInterval;
        @SerializedName("ZASA_Users")
        private Object zasaUsers;

        public CustomerListDTO(String customerId, String customerName, String customerAddress, String customerLat, String customerLong, String userId, String customerAssetid, String lastVisitDate, String lastVisitBy, String nextVisitDate, Integer visitInterval, Object zasaUsers) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.customerAddress = customerAddress;
            this.customerLat = customerLat;
            this.customerLong = customerLong;
            this.userId = userId;
            this.customerAssetid = customerAssetid;
            this.lastVisitDate = lastVisitDate;
            this.lastVisitBy = lastVisitBy;
            this.nextVisitDate = nextVisitDate;
            this.visitInterval = visitInterval;
            this.zasaUsers = zasaUsers;
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

        public String getCustomerAssetid() {
            return customerAssetid;
        }

        public void setCustomerAssetid(String customerAssetid) {
            this.customerAssetid = customerAssetid;
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

        public Object getZasaUsers() {
            return zasaUsers;
        }

        public void setZasaUsers(Object zasaUsers) {
            this.zasaUsers = zasaUsers;
        }
    }
}
