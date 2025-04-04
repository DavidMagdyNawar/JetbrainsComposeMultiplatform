package de.david_riad.internship.compose_multiplatform_jetbrains.product.domain

import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.DataError
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.EmptyResult
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.Result
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>, DataError.Remote>
    fun getFavoriteProducts(): Flow<List<Product>>
    fun isProductFavorite(id: Int): Flow<Boolean>
    suspend fun markAsFavorite(product: Product): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: Int)
}