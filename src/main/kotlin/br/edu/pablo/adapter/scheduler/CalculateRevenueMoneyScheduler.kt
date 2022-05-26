package br.edu.pablo.adapter.scheduler

import br.edu.pablo.adapter.service.CustomerAccountService
import br.edu.pablo.adapter.service.InvestmentRateService
import br.edu.pablo.adapter.service.RevenueMoneyService
import br.edu.pablo.usecase.exception.FindUpdatedRateErrorException
import com.github.michaelbull.logging.InlineLogger
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

    companion object {
        private val LOGGER = InlineLogger(CalculateRevenueMoneyScheduler::class)
    }

    @Scheduled(cron = "\${cron.daily.calculate.revenue}")
    fun calculateRevenueMoney() {
        LOGGER.info { "Iniciando Job Scheduler para calcular os rendimentos diários das contas elegíveis" }
        val accountsEligible = customerAccountService.findAccountsEligibleToRevenue()
        try {
            val investmentRateUpdated = investmentRateService.findInvestmentRateUpdated()
            for (account in accountsEligible) {
                customerAccountService.creditMoney(
                    account,
                    revenueMoneyService.newRevenueMoney(account, investmentRateUpdated)
                )
            }
            LOGGER.info { "Encerrando Job Scheduler para calcular os rendimentos diários das contas elegíveis" }
        } catch (e: Exception) {
            e.printStackTrace()
            LOGGER.error { "Erro ao buscar a taxa de investimento vigente no banco de dados" }
            throw FindUpdatedRateErrorException()
        }
    }
}
