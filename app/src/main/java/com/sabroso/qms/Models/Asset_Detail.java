package com.sabroso.qms.Models;

import com.google.gson.annotations.SerializedName;

public class Asset_Detail {


    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Asset_Detail")
    private AssetDetailDTO assetDetail;

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

    public AssetDetailDTO getAssetDetail() {
        return assetDetail;
    }

    public void setAssetDetail(AssetDetailDTO assetDetail) {
        this.assetDetail = assetDetail;
    }

    public static class AssetDetailDTO {
        @SerializedName("Asset_ID")
        private String assetId;
        @SerializedName("Asset_Title")
        private String assetTitle;
        @SerializedName("Asset_Pvalue")
        private Double assetPvalue;
        @SerializedName("Asset_Cvalue")
        private Double assetCvalue;
        @SerializedName("Department_Flag")
        private String departmentFlag;
        @SerializedName("Customer_Id")
        private String customerId;
        @SerializedName("Department")
        private Object department;
        @SerializedName("Location_Details")
        private Object locationDetails;

        public String getAssetId() {
            return assetId;
        }

        public void setAssetId(String assetId) {
            this.assetId = assetId;
        }

        public String getAssetTitle() {
            return assetTitle;
        }

        public void setAssetTitle(String assetTitle) {
            this.assetTitle = assetTitle;
        }

        public Double getAssetPvalue() {
            return assetPvalue;
        }

        public void setAssetPvalue(Double assetPvalue) {
            this.assetPvalue = assetPvalue;
        }

        public Double getAssetCvalue() {
            return assetCvalue;
        }

        public void setAssetCvalue(Double assetCvalue) {
            this.assetCvalue = assetCvalue;
        }

        public String getDepartmentFlag() {
            return departmentFlag;
        }

        public void setDepartmentFlag(String departmentFlag) {
            this.departmentFlag = departmentFlag;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public Object getDepartment() {
            return department;
        }

        public void setDepartment(Object department) {
            this.department = department;
        }

        public Object getLocationDetails() {
            return locationDetails;
        }

        public void setLocationDetails(Object locationDetails) {
            this.locationDetails = locationDetails;
        }
    }
}
