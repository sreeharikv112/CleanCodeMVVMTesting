package com.dev.ccodetest.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Custom Instrumentation Test runner.
 * Helps to configure environment with new App instance.
 */
class CustomInstrumentationRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader,
                                className: String,
                                context: Context): Application {
        return super.newApplication(cl,
            TestMainApp::class.java.name,
            context)
    }
}