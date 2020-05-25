package com.dev.ccodetest.screens.login.uc

import com.dev.ccodetest.models.login.AllPeople
import com.dev.ccodetest.repository.LoginRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Use Case class for handling Login flow.
 * Requests data from Repo.
 * Process received data into required model and reverts back to ViewModel.
 */
class LoginUseCase : KoinComponent {

    val mLoginRepo : LoginRepository by inject()

    suspend fun processLoginUseCase(query: String) : AllPeople {
        for (x in 0 until 3){
            println(" Pre Data manipulation $x")
        }
        val response =  mLoginRepo.getLoginData(query)

        for (x in 0 until 3){
            println(" Post Data manipulation $x")
        }

        return response
    }
}