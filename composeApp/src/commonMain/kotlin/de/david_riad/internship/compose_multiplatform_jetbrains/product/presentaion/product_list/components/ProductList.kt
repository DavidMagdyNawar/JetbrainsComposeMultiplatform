package de.david_riad.internship.compose_multiplatform_jetbrains.product.presentaion.product_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product

@Composable
fun ProductList(
    products: List<Product>,
    onFavoriteClick: (Product) -> Unit,
    favoriteProductIds: Set<Int>,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = scrollState,
        modifier = modifier
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductListItem(
                product = product,
                onFavoriteClick = { onFavoriteClick(product) },
                isFavorite = favoriteProductIds.contains(product.id),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}