package com.dev.ccodetest.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.ccodetest.screens.login.LoginUseCase
import com.dev.ccodetest.screens.login.LoginActivityViewModel
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Base VM Factory for creation of different VM's
 */
class BaseViewModelFactory constructor(
    private val mainDispather: CoroutineDispatcher
    ,private val ioDispatcher: CoroutineDispatcher
    ,private val useCase: LoginUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)) {
            LoginActivityViewModel(mainDispather, ioDispatcher,useCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}