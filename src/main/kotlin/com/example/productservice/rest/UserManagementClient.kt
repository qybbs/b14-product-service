package com.example.productservice.rest

import com.example.productservice.domain.dto.response.BaseResponseDto
import com.example.productservice.domain.dto.response.ResGetUsersDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "user-service",
    path = "/user-service",
)
interface UserManagementClient {
    @GetMapping("/v1/users/{id}")
    fun getUserById(
        @PathVariable(value = "id") id: Int
    ): ResponseEntity<BaseResponseDto<ResGetUsersDto?>>

    @GetMapping("/v1/data-source/users/by-ids")
    fun getUserByIds(
        @RequestParam(required = true) userIds: List<Int>
    ): ResponseEntity<BaseResponseDto<List<ResGetUsersDto>>>
}