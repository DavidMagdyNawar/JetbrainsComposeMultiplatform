package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("price") val price: Double,
    @SerialName("image") val image: String,
    )
