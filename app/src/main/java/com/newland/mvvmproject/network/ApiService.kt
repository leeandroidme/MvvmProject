package com.newland.mvvmproject.network

import com.newland.mvvmproject.bean.result.BaseResultData
import com.newland.mvvmproject.bean.result.RegisterResult
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("/user/register")
    @FormUrlEncoded
    suspend fun register(@Field("username") username:String,@Field("password") password:String,@Field("repassword") repassword:String):Response<BaseResultData<RegisterResult>>
}