package com.example.productservice.service.impl

import com.example.productservice.domain.dto.response.ResGetProductDto
import com.example.productservice.repository.MasterProductRepository
import com.example.productservice.rest.UserManagementClient
import com.example.productservice.service.MasterProductService
import org.springframework.stereotype.Service

@Service
class MasterProductServiceImpl(
    private val masterProductRepository: MasterProductRepository,
    private val userManagementClient: UserManagementClient
): MasterProductService {
    override fun findAll(): List<ResGetProductDto> {
        val rawProducts = masterProductRepository.findAll()
        val result = mutableListOf<ResGetProductDto>()
        rawProducts.forEach {
            result.add(
                ResGetProductDto(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    price = it.price,
                    stockQuantity = it.stockQuantity,
                    unitOfMeasure = it.unitOfMeasure,
                    createdBy = if (it.createdBy != null) {
                        userManagementClient.getUserById(
                            it.createdBy!!.toInt()
                        ).body?.data?.username
                    } else {
                        null
                    }
                )
            )
        }
        return result
    }

    override fun findById(id: Int): ResGetProductDto? {
        val rawProduct = masterProductRepository.findById(id)
        return if (rawProduct.isPresent) {
            ResGetProductDto(
                id = rawProduct.get().id,
                name = rawProduct.get().name,
                description = rawProduct.get().description,
                price = rawProduct.get().price,
                stockQuantity = rawProduct.get().stockQuantity,
                unitOfMeasure = rawProduct.get().unitOfMeasure,
                createdBy = if (rawProduct.get().createdBy != null) {
                    userManagementClient.getUserById(
                        rawProduct.get().createdBy!!.toInt()
                    ).body?.data?.username
                } else {
                    null
                }
            )
        } else {
            null
        }
    }
}