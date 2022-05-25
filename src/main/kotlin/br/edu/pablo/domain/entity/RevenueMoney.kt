package br.edu.pablo.domain.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class RevenueMoney(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,

    @ManyToOne
    val customerAccount: CustomerAccount,

    @ManyToOne
    val investmentRate: InvestmentRate,

    @Column(nullable = false)
    val amount: Double
) {
    @Column(nullable = false)
    val updatedDate = LocalDateTime.now()
}
