package com.example.productservice.rest

import com.example.productservice.domain.dto.response.BaseResponseDto
import com.example.productservice.domain.dto.response.ResGetUsersDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "user-service",
    path = "/user-service",
)
interface UserManagementClient {
    @GetMapping("/v1/users/{id}")
    fun getUserById(
        @PathVariable(value = "id") id: Int
    ): ResponseEntity<BaseResponseDto<ResGetUsersDto?>>
}