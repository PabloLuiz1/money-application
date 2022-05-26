package br.edu.pablo.factory

import br.edu.pablo.domain.dto.InvestmentRateDto

class InvestmentRateDtoFactory {

    companion object {
        private const val DATA = "25/05/2022"
        private const val VALOR = 0.047
    }

    fun create(): InvestmentRateDto {
        return InvestmentRateDto(DATA, VALOR)
    }
}
