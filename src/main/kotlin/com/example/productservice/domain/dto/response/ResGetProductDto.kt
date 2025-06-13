package com.example.productservice.domain.dto.response

data class ResGetProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int,
    val unitOfMeasure: String,
    val createdBy: String?,
)
