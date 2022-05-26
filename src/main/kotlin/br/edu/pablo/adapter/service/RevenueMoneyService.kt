package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.RevenueMoneyRepository
import br.edu.pablo.domain.entity.CustomerAccount
import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.RevenueMoney
import br.edu.pablo.usecase.constant.BRL_SCALE
import br.edu.pablo.usecase.constant.ONE_HUNDRED
import br.edu.pablo.usecase.constant.ZERO_BRL
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class RevenueMoneyService(
    val revenueMoneyRepository: RevenueMoneyRepository,
    val customerService: CustomerService
) {

    fun save(revenueMoney: RevenueMoney): RevenueMoney {
        return revenueMoneyRepository.save(revenueMoney)
    }

    fun findAll(): MutableList<RevenueMoney> {
        return revenueMoneyRepository.findAll()
    }

    fun findByCustomer(idCustomer: Long): MutableList<RevenueMoney> {
        val customer = customerService.findById(idCustomer)
        return revenueMoneyRepository.findRevenueMoniesByCustomerAccountCustomer(customer.get())
    }

    fun calculateRevenueAmount(revenueMoney: RevenueMoney): BigDecimal {
        val amount = (revenueMoney.customerAccount.balance * revenueMoney.investmentRate.percentage) / ONE_HUNDRED
        return BigDecimal.valueOf(amount)
    }

    fun newRevenueMoney(account: CustomerAccount, investmentRate: InvestmentRate): RevenueMoney {
        val revenueMoney = RevenueMoney(null, account, investmentRate, BigDecimal.valueOf(ZERO_BRL))
        revenueMoney.amount = calculateRevenueAmount(revenueMoney).setScale(BRL_SCALE, RoundingMode.HALF_DOWN)
        return save(revenueMoney)
    }
}
