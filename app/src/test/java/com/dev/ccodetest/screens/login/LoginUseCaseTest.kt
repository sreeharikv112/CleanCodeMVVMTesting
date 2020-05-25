package com.dev.ccodetest.screens.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.ccodetest.base.BaseUTTest
import com.dev.ccodetest.di.configureTestAppComponent
import com.dev.ccodetest.network.login.LoginAPIService
import com.dev.ccodetest.repository.LoginRepository
import com.dev.ccodetest.screens.login.uc.LoginUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class LoginUseCaseTest : BaseUTTest(){

    //Target
    private lateinit var mLoginUseCase: LoginUseCase
    //Inject login repo created with koin
    val mLoginRepo : LoginRepository by inject()
    //Inject api service created with koin
    val mAPIService : LoginAPIService by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mNextValue = "https://swapi.co/api/people/?page=2"
    val mParam = "1"
    val mCount = 87

    @Before
    fun start(){
        super.setUp()
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_login_use_case_returns_expected_value()= runBlocking{

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mLoginUseCase = LoginUseCase()

        val dataReceived = mLoginUseCase.processLoginUseCase(mParam)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.count, mCount)
        assertEquals(dataReceived.next, mNextValue)
    }

}