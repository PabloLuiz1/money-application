package br.edu.pablo.adapter.converter

import br.edu.pablo.domain.dto.InvestmentRateDto
import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateStatus
import br.edu.pablo.domain.entity.InvestmentRateStatusParams
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class InvestmentRateConverter {

    fun toEntityFromDto(dto: InvestmentRateDto): InvestmentRate {
        return InvestmentRate(
            null,
            dto.valor,
            InvestmentRateStatus(null, InvestmentRateStatusParams.UPDATED.value),
            convertStringToLocalDateWithFormat(dto.data)
        )
    }

    private fun convertStringToLocalDateWithFormat(date: String): LocalDate {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}
