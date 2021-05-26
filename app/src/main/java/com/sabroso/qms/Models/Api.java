package com.sabroso.qms.Models;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Api {


    @POST("Login")
    @FormUrlEncoded
    Call<UserDetail> Login(@Field("User_Id") String username, @Field("User_Pass") String password);


    @POST("CheckAsset")
    @FormUrlEncoded
    Call<CheckAsset> CheckAsset(@Field("Asset_ID") String Asset_ID);


    @POST("CustomerList")
    @FormUrlEncoded
    Call<customers> CustomerList(@Field("User_Id") String Asset_ID);




    @POST("AssetDetail")
    @FormUrlEncoded
    Call<Asset_Detail> AssetDetail(@Field("Asset_ID") String Asset_ID);



    @POST("AddVisit")
    @FormUrlEncoded
    Call<Asset_Detail> AddVisit(@Field("User_Id") String User_Id,@Field("Asset_ID") String Asset_ID,@Field("Customer_Id")
            String Customer_Id,@Field("Visit_Lat") String Visit_Lat,@Field("Visit_Long") String Visit_Long,
                                @Field("Visit_Remarks") String Visit_Remarks, @Field("Curr_Image_String") String Curr_Image_String);

   @POST("AddVisit")
    @FormUrlEncoded
    Call<Asset_Detail> AddVisitFake(@Field("User_Id") String User_Id,@Field("Asset_ID") String Asset_ID,@Field("Customer_Id")
            String Customer_Id,@Field("Visit_Lat") String Visit_Lat,@Field("Visit_Long") String Visit_Long,
                                @Field("Visit_Remarks") String Visit_Remarks, @Field("Original_User_Id") String Original_User_Id);
}