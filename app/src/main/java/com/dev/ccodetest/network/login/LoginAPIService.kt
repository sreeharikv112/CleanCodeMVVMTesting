package com.dev.ccodetest.network.login

import com.dev.ccodetest.models.login.AllPeople
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Login service Retrofit API.
 */
interface LoginAPIService{

    @GET("people/")
    suspend fun getLoginData(@Query("page") page:String): AllPeople

}