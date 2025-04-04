package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 4
)
@ConstructedBy(ProductDatabaseConstructor::class)
abstract class FavoriteProductDatabase : RoomDatabase() {
    abstract val favoriteProductDao: FavoriteProductDao

    companion object {
        const val DB_NAME = "product.db"
    }
}