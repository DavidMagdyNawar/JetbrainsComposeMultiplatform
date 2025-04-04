package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database

import androidx.room.RoomDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteProductDatabase>
}