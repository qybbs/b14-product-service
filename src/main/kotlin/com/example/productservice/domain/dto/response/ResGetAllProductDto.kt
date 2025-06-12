package com.example.productservice.domain.dto.response

import jakarta.persistence.Column

data class ResGetAllProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int,
    val unitOfMeasure: String,
)
