package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateRepository
import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateStatusParams
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class InvestmentRateService(
    val investmentRateRepository: InvestmentRateRepository,
    val investmentRateStatusService: InvestmentRateStatusService
) {

    fun save(investmentRate: InvestmentRate): InvestmentRate {
        investmentRate.status = investmentRateStatusService.findByDescription(investmentRate.status.description).get()
        updateToOutdatedStatus()
        return investmentRateRepository.save(investmentRate)
    }

    fun findAll(): MutableList<InvestmentRate> {
        return investmentRateRepository.findAll()
    }

    fun findEligibleToStatusUpdate(): MutableList<InvestmentRate> {
        val todayDateTime = LocalDate.now().atStartOfDay()
        return investmentRateRepository.findInvestmentRateByUpdatedDateBefore(todayDateTime)
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

    fun findInvestmentRateUpdated(): InvestmentRate {
        val updatedStatus = investmentRateStatusService.findByDescription(InvestmentRateStatusParams.UPDATED.value).get()
        return investmentRateRepository.findInvestmentRateByStatus(updatedStatus)
    }
}
