package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.network

import de.david_riad.internship.compose_multiplatform_jetbrains.core.data.safeCall
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.DataError
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.Result
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.dto.ResponseDto

import io.ktor.client.HttpClient
import io.ktor.client.request.get


private const val BASE_URL = "https://fakestoreapi.com"

class KtorRemoteProductDataSource(
    private val httpClient: HttpClient
) : RemoteProductDataSource {

    override suspend fun getProducts(

        resultLimit: Int?
    ): Result<ResponseDto, DataError.Remote> {
        return safeCall<ResponseDto> {
            httpClient.get(
                urlString = "$BASE_URL/products"
            )
        }
    }
}