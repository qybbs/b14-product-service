package com.example.productservice.domain.dto.response

import java.util.UUID

data class BaseResponseDto<T> (
    val reqId: UUID? = UUID.randomUUID(),
    val status: String = "T",
    val message: String? = "Berhasil",
    val error: Any? = null,
    val data: T? = null
)