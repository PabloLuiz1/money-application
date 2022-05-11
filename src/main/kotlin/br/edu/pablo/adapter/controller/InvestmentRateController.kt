package br.edu.pablo.adapter.controller

import br.edu.pablo.adapter.service.InvestmentRateService
import br.edu.pablo.domain.entity.InvestmentRate
import org.springframework.stereotype.Controller

@Controller
class InvestmentRateController(
    val investmentRateService: InvestmentRateService
) {

    fun getUpdatedRate(): InvestmentRate {
        return investmentRateService.getUpdatedRate()
    }
}
