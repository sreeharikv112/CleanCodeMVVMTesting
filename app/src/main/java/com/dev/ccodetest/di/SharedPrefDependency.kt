package com.dev.ccodetest.di

import com.dev.ccodetest.platform.SharedPreferenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Shared Preference DI Module.
 *
 */
val SharedPrefDependency = module {

    factory { SharedPreferenceHelper(androidContext()) }
}