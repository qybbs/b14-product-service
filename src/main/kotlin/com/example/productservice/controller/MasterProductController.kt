package com.example.productservice.controller

import com.example.productservice.domain.dto.response.ResGetAllProductDto
import com.example.productservice.service.MasterProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class MasterProductController(
    private val masterProductService: MasterProductService
) {
    @GetMapping
    fun findAll(): ResponseEntity<List<ResGetAllProductDto>> {
        return ResponseEntity.ok(masterProductService.findAll())
    }
}