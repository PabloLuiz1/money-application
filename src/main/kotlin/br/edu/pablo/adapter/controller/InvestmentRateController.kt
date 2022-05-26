package br.edu.pablo.adapter.controller

import br.edu.pablo.domain.dto.InvestmentRateDto
import br.edu.pablo.usecase.exception.ApiIntegrationErrorException
import com.github.michaelbull.logging.InlineLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/investment-rate")
class InvestmentRateController {
    companion object {
        private val LOGGER = InlineLogger(InvestmentRateController::class)
    }

    @Value("\${api.url.bcb.taxa.cdi.diaria}")
    private val URL_API_TAXA_CDI: String = ""

    @Value("\${api.bcb.taxa.cdi.diaria.parametro.formato}")
    private val PARAMETRO_FORMATO_API: String = ""

    @GetMapping("/get-updated/cdi")
    fun getUpdatedRate(): ResponseEntity<InvestmentRateDto> {
        val restTemplate = RestTemplate()
        try {
            val investmentRateDtoList =
                restTemplate.getForObject(URL_API_TAXA_CDI, arrayOf<InvestmentRateDto>()::class.java, PARAMETRO_FORMATO_API)
            val investmentRateDto = investmentRateDtoList.first()
            LOGGER.info { "Retorno da API de taxa de investimento vigente diária: $investmentRateDto" }
            return ResponseEntity.ok().body(investmentRateDto)
        } catch (e: Exception) {
            e.printStackTrace()
            LOGGER.error { "Erro ao obter a taxa de investimento vigente diária através da API" }
            throw ApiIntegrationErrorException()
        }
    }
}
