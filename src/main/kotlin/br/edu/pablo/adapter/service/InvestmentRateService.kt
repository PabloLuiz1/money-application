package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateRepository
import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.domain.entity.InvestmentRateStatusParams
import br.edu.pablo.usecase.constant.ONE_DAY
import br.edu.pablo.usecase.constant.PERCENTAGE_RATE_MAX
import br.edu.pablo.usecase.constant.PERCENTAGE_RATE_MIN
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.random.Random

@Service
class InvestmentRateService(
    val investmentRateRepository: InvestmentRateRepository,
    val investmentRateStatusService: InvestmentRateStatusService
) {

    fun getUpdatedRate(): InvestmentRate {
        val newInvestmentRate = InvestmentRate(
            null, "CDI",
            getRandomPercentageRate(),
            InvestmentRateStatus(null, InvestmentRateStatusParams.UPDATED.value),
            LocalDate.now().minusDays(ONE_DAY)
        )
        updateToOutdatedStatus()
        return save(newInvestmentRate)
    }

    fun save(investmentRate: InvestmentRate): InvestmentRate {
        investmentRate.status = investmentRateStatusService.findByDescription(investmentRate.status.description).get()
        updateToOutdatedStatus()
        return investmentRateRepository.save(investmentRate)
    }

    fun findAll(): MutableList<InvestmentRate> {
        return investmentRateRepository.findAll()
    }

    fun findEligibleToStatusUpdate(): MutableList<InvestmentRate> {
        val yesterdayDateTime = LocalDate.now().atStartOfDay()
        return investmentRateRepository.findInvestmentRateByUpdatedDateBefore(yesterdayDateTime)
    }

    fun updateToOutdatedStatus() {
        val outdatedStatus = investmentRateStatusService.findByDescription(InvestmentRateStatusParams.OUTDATED.value).get()
        val investmentsRateToUpdate = findEligibleToStatusUpdate()
        investmentsRateToUpdate.forEach {
            it.status = outdatedStatus
        }
        investmentsRateToUpdate.forEach {
            investmentRateRepository.save(it)
        }
    }

    fun getRandomPercentageRate(): Double {
        return Random.nextDouble(PERCENTAGE_RATE_MIN, PERCENTAGE_RATE_MAX)
    }

    fun findInvestmentRateUpdated(): InvestmentRate {
        val updatedStatus = investmentRateStatusService.findByDescription(InvestmentRateStatusParams.UPDATED.value).get()
        return investmentRateRepository.findInvestmentRateByStatus(updatedStatus)
    }
}
