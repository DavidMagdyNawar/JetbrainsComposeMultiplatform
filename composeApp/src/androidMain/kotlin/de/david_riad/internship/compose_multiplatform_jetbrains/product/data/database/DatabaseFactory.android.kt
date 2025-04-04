package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<FavoriteProductDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FavoriteProductDatabase.DB_NAME)

        return Room.databaseBuilder(
                context = appContext,
                klass = FavoriteProductDatabase::class.java,
                name = dbFile.absolutePath
            ).fallbackToDestructiveMigration(true)
    }
}