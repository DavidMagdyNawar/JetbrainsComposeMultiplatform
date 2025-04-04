package de.david_riad.internship.compose_multiplatform_jetbrains.product.presentaion.product_list

import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product

sealed interface ProductListAction {
    data class OnTabSelected(val index: Int) : ProductListAction
    data class OnFavoriteClick(val product: Product) : ProductListAction
}