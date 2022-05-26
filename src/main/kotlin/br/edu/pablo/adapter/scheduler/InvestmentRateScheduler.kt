package br.edu.pablo.adapter.scheduler

import br.edu.pablo.adapter.controller.InvestmentRateController
import br.edu.pablo.adapter.converter.InvestmentRateConverter
import br.edu.pablo.adapter.service.InvestmentRateService
import com.github.michaelbull.logging.InlineLogger
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class InvestmentRateScheduler(
    val investmentRateController: InvestmentRateController,
    val investmentRateService: InvestmentRateService,
    val investmentRateConverter: InvestmentRateConverter
) {

    companion object {
        private val LOGGER = InlineLogger(InvestmentRateScheduler::class)
    }

    @Scheduled(cron = "\${cron.daily.investment.rate}")
    fun getUpdatedInvestmentRate() {
        LOGGER.info { "Iniciando Job Scheduler para obter Taxa de Investimento vigente" }
        val updatedInvestmentRateDto = investmentRateController.getUpdatedRate().body
        try {
            investmentRateService.save(investmentRateConverter.toEntityFromDto(updatedInvestmentRateDto!!))
            LOGGER.info { "Encerrando Job Scheduler para obter Taxa de Investimento vigente" }
        } catch (e: Exception) {
            e.printStackTrace()
            LOGGER.error { "Erro ao tentar salvar a Taxa de Investimento vigente no banco de dados" }
        }
    }
}
