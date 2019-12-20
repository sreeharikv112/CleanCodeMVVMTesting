package com.dev.ccodetest.app

import org.koin.core.module.Module

/**
 * Helps to configure required dependencies for Instru Tests.
 * Method provideDependency can be overrided and new dependencies can be supplied.
 */
class TestMainApp : MainApp() {
    override fun provideDependency() = listOf<Module>()
}