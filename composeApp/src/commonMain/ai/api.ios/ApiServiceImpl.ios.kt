// iosMain/kotlin/com/example/network/ApiServiceImpl.ios.kt
package com.example.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.UIKit.UIDevice

actual class ApiServiceImpl : ApiService {
    private val client = HttpClient {
        // iOS-specific HttpClient configuration
        engine {
            // iOS-specific engine configuration (Darwin or other)
        }
    }

    private val baseUrl = "https://api.example.com"
    private val dataFlow = MutableSharedFlow<List<DataModel>>(replay = 1)

    actual override suspend fun fetchData(): Result<List<DataModel>> {
        return try {
            val response: List<DataModel> = client.get("$baseUrl/data")
            // Store the result in the flow for observers
            dataFlow.emit(response)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual override suspend fun postData(data: DataModel): Result<Boolean> {
        return try {
            val response: Boolean = client.post("$baseUrl/data") {
                contentType(ContentType.Application.Json)
                setBody(data)
            }
            // Refresh data after posting
            fetchData()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    actual override fun observeData(): Flow<List<DataModel>> = dataFlow
}

// iOS-specific Koin module
actual val networkModule: Module = module {
    single<ApiService> { ApiServiceImpl() }
    single { ApiRepository(get()) }
}

// iOS-specific utilities
class IOSApiUtils {
    fun getIOSPlatformHeader(): String {
        return "iOS/${UIDevice.currentDevice.systemVersion}"
    }
}