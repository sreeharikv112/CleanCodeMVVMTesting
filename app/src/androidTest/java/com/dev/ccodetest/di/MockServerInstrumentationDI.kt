package com.dev.ccodetest.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

/**
 * Mock web server Koin DI component for Instrumentation Testing
 */
val MockWebServerInstrumentationTest = module {

    factory {
        MockWebServer()
    }

}