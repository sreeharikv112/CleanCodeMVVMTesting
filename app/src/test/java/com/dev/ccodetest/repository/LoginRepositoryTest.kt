package com.dev.ccodetest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.ccodetest.base.BaseUTTest
import com.dev.ccodetest.di.configureTestAppComponent
import com.dev.ccodetest.models.login.AllPeople
import com.dev.ccodetest.network.login.LoginAPIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class LoginRepositoryTest : BaseUTTest(){

    //Target
    private lateinit var mRepo: LoginRepository
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

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_login_repo_retrieves_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = LoginRepository()

        val dataReceived = mRepo.getLoginData(mParam)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.count, mCount)
        assertEquals(dataReceived.next, mNextValue)
    }
}