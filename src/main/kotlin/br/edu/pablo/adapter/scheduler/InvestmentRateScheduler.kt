package br.edu.pablo.adapter.scheduler

import br.edu.pablo.adapter.controller.InvestmentRateController
import br.edu.pablo.adapter.service.InvestmentRateService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class InvestmentRateScheduler(
    val investmentRateController: InvestmentRateController,
    val investmentRateService: InvestmentRateService
) {

    @Scheduled(cron = "\${cron.daily.investment.rate}")
    fun getUpdatedInvestmentRate() {
        // val investmentRate = InvestmentRate(null, "CDI", investmentRateController.getUpdatedRate().)
        // investmentRateService.save(investmentRateController.getUpdatedRate())
    }
}
