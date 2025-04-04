
// commonMain/kotlin/com/example/viewmodel/DataViewModel.kt
package com.example.viewmodel

import com.example.network.ApiRepository
import com.example.network.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DataViewModel(
    private val repository: ApiRepository,
    private val coroutineScope: CoroutineScope
) {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // Observe data changes
        repository.observeDataChanges()
            .onEach { data ->
                if (data.isEmpty()) {
                    _uiState.value = UiState.Empty
                } else {
                    _uiState.value = UiState.Success(data)
                }
            }
            .launchIn(coroutineScope)

        // Initial data fetch
        fetchData()
    }

    fun fetchData() {
        _uiState.value = UiState.Loading
        coroutineScope.launch(Dispatchers.Default) {
            repository.getData()
                .onSuccess { data ->
                    if (data.isEmpty()) {
                        _uiState.value = UiState.Empty
                    } else {
                        _uiState.value = UiState.Success(data)
                    }
                }
                .onFailure { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
        }
    }

    fun saveData(data: DataModel) {
        coroutineScope.launch(Dispatchers.Default) {
            repository.saveData(data)
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object Empty : UiState()
        data class Success(val data: List<DataModel>) : UiState()
        data class Error(val message: String) : UiState()
    }
}