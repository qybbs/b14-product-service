package com.example.productservice.service.impl

import com.example.productservice.domain.dto.response.ResGetAllProductDto
import com.example.productservice.repository.MasterProductRepository
import com.example.productservice.service.MasterProductService

class MasterProductServiceImpl(
    private val masterProductRepository: MasterProductRepository
): MasterProductService {
    override fun findAll(): List<ResGetAllProductDto> {
        val rawProducts = masterProductRepository.findAll()
        val result = mutableListOf<ResGetAllProductDto>()
        rawProducts.forEach {
            result.add(
                ResGetAllProductDto(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    price = it.price,
                    stockQuantity = it.stockQuantity,
                    unitOfMeasure = it.unitOfMeasure,
                )
            )
        }
        return result
    }
}