package com.example.productservice.controller

import com.example.productservice.domain.dto.request.ReqCreateProductDto
import com.example.productservice.domain.dto.response.BaseResponseDto
import com.example.productservice.domain.dto.response.ResGetProductDto
import com.example.productservice.service.MasterProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/products")
class MasterProductController(
    private val masterProductService: MasterProductService
) {
    @GetMapping("/all")
    fun findAll(): ResponseEntity<BaseResponseDto<List<ResGetProductDto>>> {
        return ResponseEntity.ok(BaseResponseDto(data = masterProductService.findAll()))
    }

    @GetMapping("/detail/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<BaseResponseDto<ResGetProductDto>> {
        return ResponseEntity.ok(BaseResponseDto(data = masterProductService.findById(id)))
    }

    @PostMapping("/create")
    fun create(
        @RequestBody req: ReqCreateProductDto
    ): ResponseEntity<BaseResponseDto<ResGetProductDto>> {
        return ResponseEntity.ok(BaseResponseDto(data = masterProductService.create(req)))
    }
}