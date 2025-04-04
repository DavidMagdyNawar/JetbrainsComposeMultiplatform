@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ProductDatabaseConstructor: RoomDatabaseConstructor<FavoriteProductDatabase> {
    override fun initialize(): FavoriteProductDatabase
}