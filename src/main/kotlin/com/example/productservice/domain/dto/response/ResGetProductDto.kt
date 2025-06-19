package com.example.productservice.domain.dto.response

import java.io.Serializable
import java.math.BigDecimal

data class ResGetProductDto(
    val id: Int?,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val unitOfMeasure: String,
    val createdBy: String?,
): Serializable {
    companion object {
        private const val serialVersionUID: Long = 9105241754837131423L
    }
}
