package br.edu.pablo.adapter.controller

import br.edu.pablo.adapter.service.InvestmentRateService
import br.edu.pablo.domain.entity.InvestmentRate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/investment-rate")
class InvestmentRateController(
    val investmentRateService: InvestmentRateService
) {

    @GetMapping("/get-updated")
    fun getUpdatedRate(): ResponseEntity<InvestmentRate> {
        return ResponseEntity.ok().body(investmentRateService.getUpdatedRate())
    }
}
