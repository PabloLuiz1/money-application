package br.edu.pablo.factory

import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateParams
import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.usecase.constant.PERCENTAGE_RATE_RANGE
import kotlin.random.Random

class InvestmentRateFactory {

    companion object {
        private const val ID = 1L
        private const val DESCRIPTION = "CDI"
        private val STATUS = InvestmentRateStatus(1L, InvestmentRateParams.UPDATED.name)
    }

    fun createWithRandomPercentage(): InvestmentRate {
        return InvestmentRate(ID, DESCRIPTION, Random.nextDouble(PERCENTAGE_RATE_RANGE.first, PERCENTAGE_RATE_RANGE.second), STATUS)
    }
}
