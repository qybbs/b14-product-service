package com.example.productservice.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
@Table(name = "mst_products")
data class MasterProductEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "mst_products_id_seq"
    )
    @SequenceGenerator(
        name = "mst_products_id_seq", // name used in @GeneratedValue
        sequenceName = "mst_products_id_seq", // name of sequence in DB
        allocationSize = 1 // adjust based on how your DB increments
    )
    @Column(name = "id", insertable = false, updatable = false)
    var id: Int? = 0,

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "stock_quantity")
    var stockQuantity: Int = 0,

    @Column(name = "unit_of_measure")
    var unitOfMeasure: String,

    @Column(name = "created_by")
    var createdBy: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    var createdAt: Timestamp? = null,

    @Column(name = "updated_by")
    var updatedBy: String? = null,

    @UpdateTimestamp
    //tidak bisa diinsert dari kode springboot
    @Column(name = "updated_at", insertable = false, updatable = false)
    var updatedAt: Timestamp? = null,

    @Column(name = "deleted_at")
    var deletedAt: Timestamp? = null,

    @Column(name = "deleted_by")
    var deletedBy: String? = null,

    @Column(name = "is_active")
    var isActive: Boolean = true,

    @Column(name = "is_delete")
    var isDelete: Boolean = false
)
