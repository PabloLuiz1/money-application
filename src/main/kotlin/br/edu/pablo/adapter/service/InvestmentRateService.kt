package br.edu.pablo.adapter.service

import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateParams
import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.usecase.constant.PERCENTAGE_RATE_RANGE
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class InvestmentRateService {

    fun getUpdatedRate(): InvestmentRate {
        return InvestmentRate(
            null, "CDI", Random.nextDouble(PERCENTAGE_RATE_RANGE.first, PERCENTAGE_RATE_RANGE.second),
            InvestmentRateStatus(null, InvestmentRateParams.UPDATED.name)
        )
    }
}
