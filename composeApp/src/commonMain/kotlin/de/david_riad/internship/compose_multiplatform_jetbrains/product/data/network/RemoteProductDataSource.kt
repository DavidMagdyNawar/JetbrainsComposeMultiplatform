package de.david_riad.internship.compose_multiplatform_jetbrains.product.data.network

import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.DataError
import de.david_riad.internship.compose_multiplatform_jetbrains.core.domain.Result
import de.david_riad.internship.compose_multiplatform_jetbrains.product.data.dto.ResponseDto

interface RemoteProductDataSource {
    suspend fun getProducts(
        resultLimit: Int? = null
    ): Result<ResponseDto, DataError.Remote>

}