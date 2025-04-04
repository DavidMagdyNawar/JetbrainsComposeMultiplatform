package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val image: String,
    val price: Double,
    )
