// androidMain/kotlin/com/example/di/KoinSetup.android.kt
package com.example.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.module.Module
import org.koin.dsl.module

// Android-specific Koin module
actual fun platformModule(): Module = module {
    // Android-specific dependencies
}

// Android-specific initialization
fun initKoinAndroid(context: Context) = initKoin {
    androidLogger()
    androidContext(context)
}

