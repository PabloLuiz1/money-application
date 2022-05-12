package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateRepository
import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.domain.entity.InvestmentRateStatusParams
import br.edu.pablo.usecase.constant.ONE_DAY
import br.edu.pablo.usecase.constant.PERCENTAGE_RATE
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class InvestmentRateService(
    val investmentRateRepository: InvestmentRateRepository,
    val investmentRateStatusService: InvestmentRateStatusService
) {

    fun getUpdatedRate(): InvestmentRate {
        val newInvestmentRate = InvestmentRate(
            null, "CDI",
            PERCENTAGE_RATE,
            InvestmentRateStatus(null, InvestmentRateStatusParams.UPDATED.name)
        )
        return save(newInvestmentRate)
    }

    fun save(investmentRate: InvestmentRate): InvestmentRate {
        return investmentRateRepository.save(investmentRate)
    }

    fun findAll(): MutableList<InvestmentRate> {
        return investmentRateRepository.findAll()
    }

    fun findEligibleToStatusUpdate(): MutableList<InvestmentRate> {
        val yesterdayDateTime = LocalDate.now().atStartOfDay().minusDays(ONE_DAY)
        return investmentRateRepository.findInvestmentRateByUpdatedDateBefore(yesterdayDateTime)
    }

    fun updateToOutdatedStatus() {
        val outdatedStatus = investmentRateStatusService.findByDescription(InvestmentRateStatusParams.OUTDATED.name).get()
        val investmentsRateToUpdate = findEligibleToStatusUpdate()
        investmentsRateToUpdate.forEach {
            it.status = outdatedStatus
        }
        investmentsRateToUpdate.forEach {
            investmentRateRepository.save(it)
        }
    }
}
