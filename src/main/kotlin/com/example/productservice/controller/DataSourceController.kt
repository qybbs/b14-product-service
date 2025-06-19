package com.example.productservice.controller

import com.example.productservice.domain.dto.response.BaseResponseDto
import com.example.productservice.domain.dto.response.ResGetProductDto
import com.example.productservice.service.MasterProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/data-source")
class DataSourceController(
    private val masterProductService: MasterProductService
) {
    @GetMapping("/products/{id}")
    fun getUsersByIds(
        @PathVariable(value = "id") id: Int
    ): ResponseEntity<BaseResponseDto<ResGetProductDto>>{
        return ResponseEntity.ok(
            BaseResponseDto(
                data = masterProductService.findById(id)
            )
        )
    }

    @GetMapping("/products/by-ids")
    fun getProductsByIds(
        @RequestParam(required = true) productIds: List<Int>
    ): ResponseEntity<BaseResponseDto<List<ResGetProductDto>>>{
        return ResponseEntity.ok(
            BaseResponseDto(
                data = masterProductService.findProductsByIds(productIds)
            )
        )
    }
}