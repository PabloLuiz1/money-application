package br.edu.pablo.factory

import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.domain.entity.InvestmentRateStatusParams

class InvestmentRateStatusFactory {

    companion object {
        private const val ID = 1L
        private val DESCRIPTION_UPDATED = InvestmentRateStatusParams.UPDATED.name
        private val DESCRIPTION_OUTDATED = InvestmentRateStatusParams.OUTDATED.name
    }

    fun createUpdated(): InvestmentRateStatus {
        return InvestmentRateStatus(ID, DESCRIPTION_UPDATED)
    }

    fun createOutdated(): InvestmentRateStatus {
        return InvestmentRateStatus(ID, DESCRIPTION_OUTDATED)
    }
}
