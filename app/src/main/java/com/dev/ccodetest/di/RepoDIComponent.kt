package com.dev.ccodetest.di

import com.dev.ccodetest.repository.LoginRepository
import org.koin.dsl.module

/**
 * Repository DI module.
 * Provides Repo dependency.
 */
val RepoDependency = module {

    factory {
        LoginRepository()
    }

}