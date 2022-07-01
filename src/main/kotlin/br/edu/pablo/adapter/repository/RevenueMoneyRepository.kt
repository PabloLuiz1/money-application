package br.edu.pablo.adapter.repository

import br.edu.pablo.domain.entity.Customer
import br.edu.pablo.domain.entity.RevenueMoney
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.LocalDateTime

@Repository
interface RevenueMoneyRepository : JpaRepository<RevenueMoney, Long> {

    fun findRevenueMoniesByCustomerAccountCustomer(customer: Customer): MutableList<RevenueMoney>

    @Query(
        value = "SELECT SUM(rm.amount) FROM RevenueMoney rm " +
            "INNER JOIN CustomerAccount ca " +
            "ON rm.customerAccount = ca " +
            "INNER JOIN Customer c " +
            "ON ca.customer = :customer " +
            "WHERE rm.updatedDate BETWEEN :startOfYear AND :endOfYear"
    )
    fun findSumAmountByCustomer(customer: Customer, startOfYear: LocalDateTime, endOfYear: LocalDateTime): BigDecimal
}
