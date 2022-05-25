package br.edu.pablo.factory

import br.edu.pablo.domain.entity.RevenueMoney
import java.math.BigDecimal

class RevenueMoneyFactory {

    companion object {
        private const val ID = 1L
        private const val AMOUNT = 1.2
        private val CUSTOMER_ACCOUNT = CustomerAccountFactory().create()
        private val INVESTMENT_RATE = InvestmentRateFactory().createWithDefaultPercentage()
    }

    fun create(): RevenueMoney {
        return RevenueMoney(ID, CUSTOMER_ACCOUNT, INVESTMENT_RATE, BigDecimal.valueOf(AMOUNT))
    }
}
