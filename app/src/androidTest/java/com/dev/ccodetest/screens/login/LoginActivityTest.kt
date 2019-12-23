package com.dev.ccodetest.screens.login

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dev.ccodetest.R
import com.dev.ccodetest.base.BaseUITest
import com.dev.ccodetest.di.generateTestAppComponent
import com.dev.ccodetest.helpers.recyclerItemAtPosition
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class LoginActivityTest : BaseUITest(){

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java, true, false)

    //Inject login use case created with koin
    val mLoginUseCase : LoginUseCase by inject()
    //Inject Mockwebserver created with koin
    val  mMockWebServer : MockWebServer by inject()

    val mNameTestOne = "Luke Skywalker"
    val mDOBTestOne = "19BBY"
    val mNameTestTwo = "Obi-Wan Kenobi"
    val mDOBTestTwo = "57BBY"

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {
        mActivityTestRule.launchActivity(null)

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(1000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.landingListRecyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mNameTestOne))
                    )))

        onView(withId(R.id.landingListRecyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mDOBTestOne))
                    )))

        //Scroll to last index in json
        onView(withId(R.id.landingListRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<LoginRecyclerViewAdapter.LoginFragViewHolder>(9))

        //Check if item at 9th position is having 9th element in json
        onView(withId(R.id.landingListRecyclerView))
            .check(matches(recyclerItemAtPosition(9, ViewMatchers.hasDescendant(withText(mNameTestTwo)))))

        onView(withId(R.id.landingListRecyclerView))
            .check(matches(recyclerItemAtPosition(9, ViewMatchers.hasDescendant(withText(mDOBTestTwo)))))

    }
}