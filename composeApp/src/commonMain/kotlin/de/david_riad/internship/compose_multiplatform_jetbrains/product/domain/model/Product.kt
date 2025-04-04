package de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
)