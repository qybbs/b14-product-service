package com.example.productservice.repository

import com.example.productservice.domain.entity.MasterProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MasterProductRepository: JpaRepository<MasterProductEntity, Int> {
    @Query("""
        SELECT p FROM MasterProductEntity p
        WHERE p.id IN (:ids)
    """, nativeQuery = false)
    fun findAllByIds(ids: List<Int>): List<MasterProductEntity>
}