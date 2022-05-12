package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateStatusRepository
import br.edu.pablo.domain.entity.InvestmentRateStatus
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class InvestmentRateStatusService(
    val investmentRateStatusRepository: InvestmentRateStatusRepository
) {

    fun findByDescription(description: String): Optional<InvestmentRateStatus> {
        return investmentRateStatusRepository.findInvestmentRateStatusByDescription(description)
    }
}
