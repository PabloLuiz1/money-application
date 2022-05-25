package br.edu.pablo.adapter.scheduler

import br.edu.pablo.adapter.service.CustomerAccountService
import br.edu.pablo.adapter.service.InvestmentRateService
import br.edu.pablo.adapter.service.RevenueMoneyService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class CalculateRevenueMoneyScheduler(
    val investmentRateService: InvestmentRateService,
    val customerAccountService: CustomerAccountService,
    val revenueMoneyService: RevenueMoneyService
) {

    @Scheduled(cron = "\${cron.daily.calculate.revenue}")
    fun calculateRevenueMoney() {
        val accountsEligible = customerAccountService.findAccountsEligibleToRevenue()
        val investmentRateUpdated = investmentRateService.findInvestmentRateUpdated()
        for (account in accountsEligible) {
            customerAccountService.creditMoney(account, revenueMoneyService.newRevenueMoney(account, investmentRateUpdated))
        }
    }
}
