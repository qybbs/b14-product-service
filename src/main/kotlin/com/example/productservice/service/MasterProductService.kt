package com.example.productservice.service

import com.example.productservice.domain.dto.response.ResGetProductDto

interface MasterProductService {
    fun findAll(): List<ResGetProductDto>
    fun findById(id: Int): ResGetProductDto?
}