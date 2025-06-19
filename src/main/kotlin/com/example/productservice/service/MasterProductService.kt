package com.example.productservice.service

import com.example.productservice.domain.dto.request.ReqCreateProductDto
import com.example.productservice.domain.dto.response.ResGetProductDto

interface MasterProductService {
    fun findAll(): List<ResGetProductDto>
    fun findById(id: Int): ResGetProductDto?
    fun findProductsByIds(productsIds: List<Int>): List<ResGetProductDto>
    fun create(req: ReqCreateProductDto): ResGetProductDto
}