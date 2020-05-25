package com.dev.ccodetest.di

import com.dev.ccodetest.screens.login.uc.LoginUseCase
import org.koin.dsl.module

/**
 * Use case DI module.
 * Provide Use case dependency.
 */
val UseCaseDependency = module {

    factory {
        LoginUseCase()
    }
}