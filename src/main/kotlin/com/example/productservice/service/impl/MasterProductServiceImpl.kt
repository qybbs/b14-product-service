package com.example.productservice.service.impl

import com.example.productservice.domain.constant.Constant
import com.example.productservice.domain.dto.request.ReqCreateProductDto
import com.example.productservice.domain.dto.response.ResGetProductDto
import com.example.productservice.domain.entity.MasterProductEntity
import com.example.productservice.exception.CustomException
import com.example.productservice.repository.MasterProductRepository
import com.example.productservice.rest.UserManagementClient
import com.example.productservice.service.MasterProductService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class MasterProductServiceImpl(
    private val masterProductRepository: MasterProductRepository,
    private val userManagementClient: UserManagementClient,
    private val httpServletRequest: HttpServletRequest
): MasterProductService {
    @Cacheable(
        value = ["findAllProducts"],
    )
    override fun findAll(): List<ResGetProductDto> {
        val rawProducts = masterProductRepository.findAll()

        val createdByIds = rawProducts.mapNotNull { it.createdBy?.toInt() }.distinct()
        val createdBys = if (createdByIds.isNotEmpty()) {
            userManagementClient.getUserByIds(createdByIds).body?.data
        } else {
            emptyList()
        }

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
                        createdBys?.find { user -> user.id == it.createdBy!!.toInt() }?.username
                    } else {
                        null
                    }
                )
            )
        }
        return result
    }

    @Cacheable(
        value = ["findProductById"],
        key = "{#id}",
    )
    override fun findById(id: Int): ResGetProductDto? {
        val rawProduct = masterProductRepository.findById(id)
            .orElseThrow {
                CustomException(
                    "Produk dengan id $id tidak ditemukan",
                    HttpStatus.NOT_FOUND.value()
                )
            }

        return ResGetProductDto(
            id = rawProduct.id,
            name = rawProduct.name,
            description = rawProduct.description,
            price = rawProduct.price,
            stockQuantity = rawProduct.stockQuantity,
            unitOfMeasure = rawProduct.unitOfMeasure,
            createdBy = if (rawProduct.createdBy != null) {
                userManagementClient.getUserById(
                    rawProduct.createdBy!!.toInt()
                ).body?.data?.username
            } else {
                null
            }
        )
    }

    override fun findProductsByIds(productsIds: List<Int>): List<ResGetProductDto> {
        val rawProducts = masterProductRepository.findAllByIds(productsIds)
        val createdByIds = rawProducts.mapNotNull { it.createdBy?.toInt() }.distinct()
        val createdBys = if (createdByIds.isNotEmpty()) {
            userManagementClient.getUserByIds(createdByIds).body?.data
        } else {
            emptyList()
        }
        val result = mutableListOf<ResGetProductDto>()
        rawProducts.forEach { product ->
            result.add(
                ResGetProductDto(
                    id = product.id,
                    name = product.name,
                    description = product.description,
                    price = product.price,
                    stockQuantity = product.stockQuantity,
                    unitOfMeasure = product.unitOfMeasure,
                    createdBy = if (product.createdBy != null) {
                        createdBys?.find { user -> user.id == product.createdBy!!.toInt() }?.username
                    } else {
                        null
                    }
                )
            )
        }
        return result
    }

    @CacheEvict(
        value = ["findAllProducts", "findProductById"],
        allEntries = true
    )
    override fun create(req: ReqCreateProductDto): ResGetProductDto {
        val createdById = httpServletRequest.getHeader(Constant.HEADER_USER_ID)
        val createdByRole = httpServletRequest.getHeader(Constant.HEADER_USER_ROLE)

        println("createdById: $createdById, createdByRole: $createdByRole")

        if (createdByRole != "admin") {
            throw CustomException(
                "Hanya admin yang dapat membuat produk",
                HttpStatus.FORBIDDEN.value()
            )
        }

        val productRaw = MasterProductEntity(
            name = req.name,
            description = req.description,
            price = req.price,
            stockQuantity = req.stockQuantity,
            unitOfMeasure = req.unitOfMeasure,
            createdBy = createdById
        )

        val savedProduct = masterProductRepository.save(productRaw)

        return ResGetProductDto(
            id = savedProduct.id,
            name = savedProduct.name,
            description = savedProduct.description,
            price = savedProduct.price,
            stockQuantity = savedProduct.stockQuantity,
            unitOfMeasure = savedProduct.unitOfMeasure,
            createdBy = if (savedProduct.createdBy != null) {
                userManagementClient.getUserById(
                    savedProduct.createdBy!!.toInt()
                ).body?.data?.username
            } else {
                null
            }
        )
    }
}