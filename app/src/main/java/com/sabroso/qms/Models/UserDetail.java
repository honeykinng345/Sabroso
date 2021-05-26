package com.sabroso.qms.Models;

import java.util.List;

public class UserDetail {
    @com.google.gson.annotations.SerializedName("Status")
    private Integer status;
    @com.google.gson.annotations.SerializedName("Message")
    private String message;
    @com.google.gson.annotations.SerializedName("User_Details")
    private UserDetailsDTO userDetails;

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

    public UserDetailsDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsDTO userDetails) {
        this.userDetails = userDetails;
    }

    public static class UserDetailsDTO {
        @com.google.gson.annotations.SerializedName("User_ID")
        private String userId;
        @com.google.gson.annotations.SerializedName("User_Type_Code")
        private Integer userTypeCode;
        @com.google.gson.annotations.SerializedName("User_Pass")
        private String userPass;
        @com.google.gson.annotations.SerializedName("User_Name")
        private String userName;
        @com.google.gson.annotations.SerializedName("ERP_ID")
        private Object erpId;
        @com.google.gson.annotations.SerializedName("RO_ID")
        private Object roId;
        @com.google.gson.annotations.SerializedName("User_Active")
        private String userActive;
        @com.google.gson.annotations.SerializedName("Field_Flag")
        private String fieldFlag;
        @com.google.gson.annotations.SerializedName("ZASA_Customers")
        private List<?> zasaCustomers;
        @com.google.gson.annotations.SerializedName("ZASA_Customers_Visit")
        private List<?> zasaCustomersVisit;
        @com.google.gson.annotations.SerializedName("ZASA_Customers_Visit_Fake")
        private List<?> zasaCustomersVisitFake;
        @com.google.gson.annotations.SerializedName("ZASA_RO")
        private Object zasaRo;
        @com.google.gson.annotations.SerializedName("ZASA_Users_Type")
        private Object zasaUsersType;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Integer getUserTypeCode() {
            return userTypeCode;
        }

        public void setUserTypeCode(Integer userTypeCode) {
            this.userTypeCode = userTypeCode;
        }

        public String getUserPass() {
            return userPass;
        }

        public void setUserPass(String userPass) {
            this.userPass = userPass;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getErpId() {
            return erpId;
        }

        public void setErpId(Object erpId) {
            this.erpId = erpId;
        }

        public Object getRoId() {
            return roId;
        }

        public void setRoId(Object roId) {
            this.roId = roId;
        }

        public String getUserActive() {
            return userActive;
        }

        public void setUserActive(String userActive) {
            this.userActive = userActive;
        }

        public String getFieldFlag() {
            return fieldFlag;
        }

        public void setFieldFlag(String fieldFlag) {
            this.fieldFlag = fieldFlag;
        }

        public List<?> getZasaCustomers() {
            return zasaCustomers;
        }

        public void setZasaCustomers(List<?> zasaCustomers) {
            this.zasaCustomers = zasaCustomers;
        }

        public List<?> getZasaCustomersVisit() {
            return zasaCustomersVisit;
        }

        public void setZasaCustomersVisit(List<?> zasaCustomersVisit) {
            this.zasaCustomersVisit = zasaCustomersVisit;
        }

        public List<?> getZasaCustomersVisitFake() {
            return zasaCustomersVisitFake;
        }

        public void setZasaCustomersVisitFake(List<?> zasaCustomersVisitFake) {
            this.zasaCustomersVisitFake = zasaCustomersVisitFake;
        }

        public Object getZasaRo() {
            return zasaRo;
        }

        public void setZasaRo(Object zasaRo) {
            this.zasaRo = zasaRo;
        }

        public Object getZasaUsersType() {
            return zasaUsersType;
        }

        public void setZasaUsersType(Object zasaUsersType) {
            this.zasaUsersType = zasaUsersType;
        }
    }
}
