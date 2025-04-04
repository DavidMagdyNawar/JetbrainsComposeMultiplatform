// iosMain/kotlin/com/example/di/KoinSetup.ios.kt
package com.example.di

import org.koin.core.module.Module
import org.koin.dsl.module

// iOS-specific Koin module
actual fun platformModule(): Module = module {
    // iOS-specific dependencies
}

// iOS-specific initialization helper
fun initKoinIOS() = initKoin()