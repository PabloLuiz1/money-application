package br.edu.pablo.adapter.repository

import br.edu.pablo.domain.entity.Customer
import br.edu.pablo.domain.entity.RevenueMoney
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RevenueMoneyRepository : JpaRepository<RevenueMoney, Long> {

    fun findRevenueMoniesByCustomerAccountCustomer(customer: Customer): MutableList<RevenueMoney>
}
