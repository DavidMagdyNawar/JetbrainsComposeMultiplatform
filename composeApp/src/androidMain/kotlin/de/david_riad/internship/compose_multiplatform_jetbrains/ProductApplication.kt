package de.david_riad.internship.compose_multiplatform_jetbrains

import android.app.Application
import de.david_riad.internship.compose_multiplatform_jetbrains.di.initKoin
import org.koin.android.ext.koin.androidContext

class ProductApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ProductApplication)
        }
    }
}