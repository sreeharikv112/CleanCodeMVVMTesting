package com.dev.ccodetest.screens.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.ccodetest.base.BaseUTTest
import com.dev.ccodetest.di.configureTestAppComponent
import com.dev.ccodetest.models.login.AllPeople
import com.dev.ccodetest.platform.LiveDataWrapper
import com.dev.ccodetest.screens.login.uc.LoginUseCase
import com.dev.ccodetest.screens.login.vm.LoginActivityViewModel
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class LoginActivityViewModelTest: BaseUTTest(){

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mLoginActivityViewModel: LoginActivityViewModel

    val mDispatcher = Dispatchers.Unconfined

    @MockK
    lateinit var mLoginUseCase: LoginUseCase

    val mParam = "1"
    val mNextValue = "https://swapi.co/api/people/?page=2"

    @Before
    fun start(){
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_login_view_model_data_populates_expected_value(){

        mLoginActivityViewModel =
            LoginActivityViewModel(
                mDispatcher,
                mDispatcher,
                mLoginUseCase
            )
        val sampleResponse = getJson("success_resp_list.json")
        var jsonObj = Gson().fromJson(sampleResponse, AllPeople::class.java)
        //Make sure login use case returns expected response when called
        coEvery { mLoginUseCase.processLoginUseCase(any()) } returns jsonObj
        mLoginActivityViewModel.mAllPeopleResponse.observeForever {  }

        mLoginActivityViewModel.requestLoginActivityData(mParam)

        assert(mLoginActivityViewModel.mAllPeopleResponse.value != null)
        assert(
            mLoginActivityViewModel.mAllPeopleResponse.value!!.responseStatus
                == LiveDataWrapper.RESPONSESTATUS.SUCCESS)
        val testResult = mLoginActivityViewModel.mAllPeopleResponse.value as LiveDataWrapper<AllPeople>
        assertEquals(testResult.response!!.next,mNextValue)
    }
}