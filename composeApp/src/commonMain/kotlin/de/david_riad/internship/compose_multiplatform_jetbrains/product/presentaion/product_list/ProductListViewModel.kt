package de.david_riad.internship.compose_multiplatform_jetbrains.product.presentaion.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.onError
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.onSuccess
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.model.Product
import de.david_riad.internship.compose_multiplatform_jetbrains.product.domain.ProductRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var cachedProducts = emptyList<Product>()
    private var job: Job? = null
    private var observeFavoriteJob: Job? = null

    private val _state = MutableStateFlow(ProductListState())
    val state = _state
        .onStart {
            if (cachedProducts.isEmpty()) {
                observeProducts()
            }
            observeFavoriteProducts()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
            is ProductListAction.OnFavoriteClick -> {
                toggleFavorite(action.product)
            }
        }
    }

    private fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val isFavorite = _state.value.favoriteProductIds.contains(product.id)
            if (isFavorite) productRepository.deleteFromFavorites(product.id)
            else productRepository.markAsFavorite(product)
        }
    }

    private fun observeFavoriteProducts() {
        observeFavoriteJob?.cancel()
        observeFavoriteJob = productRepository
            .getFavoriteProducts()
            .onEach { favoriteProducts ->
                _state.update {
                    it.copy(
                        favoriteProducts = favoriteProducts,
                        favoriteProductIds = favoriteProducts.map { product -> product.id }.toSet()
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeProducts() {
        state.onStart {
            job?.cancel()
            job = getProducts()
        }
            .launchIn(viewModelScope)
    }

    private fun getProducts() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        productRepository
            .getProducts()
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        result = result
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        result = emptyList(),
                        isLoading = false,
                        errorMessage = error.toString()
                    )
                }
            }
    }
}