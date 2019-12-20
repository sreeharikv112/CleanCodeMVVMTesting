package com.dev.ccodetest.repository

import com.dev.ccodetest.models.login.AllPeople
import com.dev.ccodetest.network.login.LoginAPIService
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Repository for Login Flow.
 * Requests data from either Network or DB.
 *
 */
class LoginRepository : KoinComponent {

    val mLoginAPIService: LoginAPIService by inject()
    /**
     * Request data from backend
     */
    suspend fun getLoginData(query: String): AllPeople {

        return processDataFetchLogic(query)

    }

    suspend fun processDataFetchLogic(parameter:String): AllPeople{

        for (x in 0 until 3){
            println(" Data manipulation prior to REST API request if required $x")
        }

        val dataReceived = mLoginAPIService.getLoginData(parameter)

        for (x in 0 until 3){
            println(" Data manipulation post REST API request if required $x")
        }

        return dataReceived
    }


}