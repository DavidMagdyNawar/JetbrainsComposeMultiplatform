package de.david_riad.internship.compose_multiplatform_jetbrains.product.presentaion.product_list

import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product


data class ProductListState(
    val result: List<Product> = emptyList(),
    val favoriteProducts: List<Product> = emptyList(),
    val favoriteProductIds: Set<Int> = emptySet(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: String? = null
)