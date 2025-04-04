// commonMain/kotlin/com/example/network/ApiService.kt
package com.example.network

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

// Common interface that will be implemented platform-specifically
interface ApiService {
    suspend fun fetchData(): Result<List<DataModel>>
    suspend fun postData(data: DataModel): Result<Boolean>
    fun observeData(): Flow<List<DataModel>>
}

// Data model used across platforms
data class DataModel(
    val id: String,
    val title: String,
    val description: String
)

// Expected declaration that will have platform-specific implementations
expect class ApiServiceImpl() : ApiService

// Common repository that uses the platform-specific implementation
class ApiRepository(private val apiService: ApiService) {
    suspend fun getData(): Result<List<DataModel>> =
        apiService.fetchData()

    suspend fun saveData(data: DataModel): Result<Boolean> =
        apiService.postData(data)

    fun observeDataChanges(): Flow<List<DataModel>> =
        apiService.observeData()
}

// Koin module for common dependency injection
expect val networkModule: org.koin.core.module.Module