package com.example.productservice.service

import com.example.productservice.domain.dto.response.ResGetAllProductDto
import org.springframework.stereotype.Service

@Service
interface MasterProductService {
    fun findAll(): List<ResGetAllProductDto>
}