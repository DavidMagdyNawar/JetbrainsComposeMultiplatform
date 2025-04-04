package de.david_riad.internship.compose_multiplatform_jetbrains.di

import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database.DatabaseFactory

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single {DatabaseFactory() }
    }