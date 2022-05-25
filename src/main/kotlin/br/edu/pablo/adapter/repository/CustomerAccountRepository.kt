package br.edu.pablo.adapter.repository

import br.edu.pablo.domain.entity.CustomerAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerAccountRepository : JpaRepository<CustomerAccount, Long> {

    fun findAllByBalanceGreaterThan(balance: Double): MutableList<CustomerAccount>
}
