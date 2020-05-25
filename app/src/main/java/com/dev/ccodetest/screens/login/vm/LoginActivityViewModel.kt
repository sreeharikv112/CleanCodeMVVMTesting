package com.dev.ccodetest.screens.login.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.ccodetest.models.login.AllPeople
import com.dev.ccodetest.platform.LiveDataWrapper
import com.dev.ccodetest.screens.login.uc.LoginUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

/**
 * Login View Model.
 * Handles connecting with corresponding Use Case.
 * Getting back data to View.
 */

class LoginActivityViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val useCase: LoginUseCase
) : ViewModel(), KoinComponent
     {

    var mAllPeopleResponse = MutableLiveData<LiveDataWrapper<AllPeople>>()
    val mLoadingLiveData = MutableLiveData<Boolean>()
     private val job = SupervisorJob()
     val mUiScope = CoroutineScope(mainDispatcher + job)
     val mIoScope = CoroutineScope(ioDispatcher + job)

    init {
        //call reset to reset all VM data as required
        resetValues()
    }

    //Reset any member as per flow
    fun resetValues() {

    }

    //can be called from View explicitly if required
    //Keep default scope
    fun requestLoginActivityData(param:String) {
        if(mAllPeopleResponse.value == null){
            mUiScope.launch {
                mAllPeopleResponse.value = LiveDataWrapper.loading()
                setLoadingVisibility(true)
                try {
                    val data = mIoScope.async {
                        return@async useCase.processLoginUseCase(param)
                    }.await()
                    try {
                        mAllPeopleResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
                    setLoadingVisibility(false)
                } catch (e: Exception) {
                    setLoadingVisibility(false)
                    mAllPeopleResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}