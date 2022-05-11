package br.edu.pablo.domain.entity

import br.edu.pablo.usecase.exception.InvalidCustomerGenderException
import org.apache.commons.lang3.StringUtils

enum class CustomerGenderParams(val value: String) {
    MALE("MASCULINO"),
    FEMALE("FEMININO");

    companion object {
        fun fromGender(value: String): CustomerGenderParams {
            return if (StringUtils.isBlank(value)) {
                throw InvalidCustomerGenderException()
            } else {
                values().first { gender -> gender.value == value }
            }
        }
    }
}
