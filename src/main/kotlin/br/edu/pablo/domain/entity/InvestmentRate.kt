package br.edu.pablo.domain.entity

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class InvestmentRate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,

    @Column(nullable = false)
    val percentage: Double,

    @ManyToOne
    var status: InvestmentRateStatus,

    @Column(nullable = false)
    val effectiveDate: LocalDate
) {
    @Column(nullable = false)
    var updatedDate = LocalDateTime.now()
}
