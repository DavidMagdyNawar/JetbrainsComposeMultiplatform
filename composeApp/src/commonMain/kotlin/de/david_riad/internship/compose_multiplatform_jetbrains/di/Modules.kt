package de.david_riad.internship.compose_multiplatform_jetbrains.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import de.david_riad.internship.compose_multiplatform_jetbrains.core.data.HttpClientFactory
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database.FavoriteProductDatabase
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.ProductRepository
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.network.RemoteProductDataSource
import de.david_riad.internship.compose_multiplatform_jetbrains.product.presentaion.product_list.ProductListViewModel
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.network.KtorRemoteProductDataSource
import  de.david_riad.internship.compose_multiplatform_jetbrains.product.data.repository.DefaultProductRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

import org.koin.core.module.Module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteProductDataSource).bind<RemoteProductDataSource>()
    singleOf(::DefaultProductRepository).bind<ProductRepository>()

    single {
        get<de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database.DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteProductDatabase>().favoriteProductDao }

    viewModelOf(::ProductListViewModel)
}