package com.example.productservice.domain.dto.request

import java.math.BigDecimal

data class ReqCreateProductDto(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stockQuantity: Int = 0,
    val unitOfMeasure: String,
)
