package br.edu.pablo.domain.entity

import br.edu.pablo.usecase.exception.InvalidCustomerGenderException
import org.apache.commons.lang3.StringUtils

enum class InvestmentRateStatusParams(val value: String) {
    UPDATED("VIGENTE"),
    OUTDATED("DESATUALIZADA");

    companion object {
        fun fromStatus(value: String): InvestmentRateStatusParams {
            return if (StringUtils.isBlank(value)) {
                throw InvalidCustomerGenderException()
            } else {
                values().first { gender -> gender.value == value }
            }
        }
    }
}
