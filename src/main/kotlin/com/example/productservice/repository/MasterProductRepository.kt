package com.example.productservice.repository

import com.example.productservice.domain.entity.MasterProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MasterProductRepository: JpaRepository<MasterProductEntity, Int> {}