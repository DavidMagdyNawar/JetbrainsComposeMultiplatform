package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.repository

import androidx.sqlite.SQLiteException
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.DataError
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.map
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.ProductRepository
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.mappers.toProduct
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.mappers.toProductEntity
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.Result
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.EmptyResult
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database.FavoriteProductDao
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.network.RemoteProductDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultProductRepository(
    private val remoteProductDataSource: RemoteProductDataSource,
    private val favoriteProductDao: FavoriteProductDao
): ProductRepository {
    override suspend fun getProducts(): Result<List<Product>, DataError.Remote> {
        return remoteProductDataSource
            .getProducts()
            .map { productList ->
                productList.map { it.toProduct() }
            }
    }


    override fun getFavoriteProducts(): Flow<List<Product>> {
        return favoriteProductDao
            .getFavoriteProducts()
            .map { productEntities ->
                productEntities.map { it.toProduct() }
            }
    }

    override fun isProductFavorite(id: Int): Flow<Boolean> {
        return favoriteProductDao
            .getFavoriteProducts()
            .map { productEntities ->
                productEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(product: Product): EmptyResult<DataError.Local> {
        return try {
            favoriteProductDao.upsert(product.toProductEntity())
            Result.Success(Unit)
        } catch(e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: Int) {
        favoriteProductDao.deleteFavoriteProduct(id)
    }
}