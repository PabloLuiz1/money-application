package br.edu.pablo.factory

import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.domain.entity.InvestmentRateStatusParams
import br.edu.pablo.usecase.constant.ONE_DAY
import br.edu.pablo.usecase.constant.PERCENTAGE_RATE
import java.time.LocalDateTime

class InvestmentRateFactory {

    companion object {
        private const val ID = 1L
        private const val DESCRIPTION = "CDI"
        private val STATUS_UPDATED = InvestmentRateStatus(1L, InvestmentRateStatusParams.UPDATED.name)
    }

    fun createWithDefaultPercentage(): InvestmentRate {
        return InvestmentRate(ID, DESCRIPTION, PERCENTAGE_RATE, STATUS_UPDATED)
    }

    fun createOutdatedRate(): InvestmentRate {
        val investmentRate = InvestmentRate(ID, DESCRIPTION, PERCENTAGE_RATE, STATUS_UPDATED)
        investmentRate.updatedDate = LocalDateTime.now().minusDays(ONE_DAY)
        return investmentRate
    }
}
