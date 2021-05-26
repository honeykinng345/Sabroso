package com.sabroso.qms.Models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckAsset implements Serializable
{

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Customer_Detail")
    @Expose
    private CustomerDetail customerDetail;
    @SerializedName("Customer_List")
    @Expose
    private Object customerList;
    private final static long serialVersionUID = -119639130291072822L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CheckAsset() {
    }

    /**
     *
     * @param customerDetail
     * @param customerList
     * @param message
     * @param status
     */
    public CheckAsset(Integer status, String message, CustomerDetail customerDetail, Object customerList) {
        super();
        this.status = status;
        this.message = message;
        this.customerDetail = customerDetail;
        this.customerList = customerList;
    }

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

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public Object getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Object customerList) {
        this.customerList = customerList;
    }

}