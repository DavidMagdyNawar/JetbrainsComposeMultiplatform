package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.mappers

import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.database.ProductEntity
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.dto.ProductDto
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product


fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        image = image,
        price = price
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        image = image,
        price = price
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        image = image,
        price = price
    )
}