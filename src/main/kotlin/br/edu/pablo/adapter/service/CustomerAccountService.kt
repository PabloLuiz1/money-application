package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.CustomerAccountRepository
import br.edu.pablo.domain.entity.CustomerAccount
import br.edu.pablo.domain.entity.RevenueMoney
import br.edu.pablo.usecase.constant.ZERO_BRL
import org.springframework.stereotype.Service

@Service
class CustomerAccountService(
    val customerAccountRepository: CustomerAccountRepository
) {

    fun findAccountsEligibleToRevenue(): MutableList<CustomerAccount> {
        return customerAccountRepository.findAllByBalanceGreaterThan(ZERO_BRL)
    }

    fun save(customerAccount: CustomerAccount): CustomerAccount {
        return customerAccountRepository.save(customerAccount)
    }

    fun creditMoney(customerAccount: CustomerAccount, revenueMoney: RevenueMoney) {
        customerAccount.balance += revenueMoney.amount.toDouble()
        save(customerAccount)
    }
}
