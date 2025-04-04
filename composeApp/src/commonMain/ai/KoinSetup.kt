// commonMain/kotlin/com/example/di/KoinSetup.kt
package com.example.di

import com.example.network.networkModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

// A common module for shared dependencies
val commonModule = org.koin.dsl.module {
    // Common dependencies
}

// Platform-specific initialization
expect fun platformModule(): Module

// Main setup function for initializing Koin
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        commonModule,
        networkModule,
        platformModule()
    )
}

// Simplified initialization without custom declaration
fun initKoin() = initKoin {}


