package com.example.productservice.domain.dto.response

data class BaseResponseDto<T> (
    val message: String? = "Berhasil",
    val status: String = "T",
    val data: T? = null
)