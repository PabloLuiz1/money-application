package br.edu.pablo.factory

import br.edu.pablo.domain.entity.Customer
import br.edu.pablo.domain.entity.CustomerGender
import br.edu.pablo.domain.entity.CustomerGenderParams
import java.time.LocalDate

class CustomerFactory {

    companion object {
        private const val ID = 1L
        private const val NAME = "Pablo Luiz Ribeiro"
        private const val CPF = "12345678900"
        private const val EMAIL = "pabloluiz@gmail.com"
        private val GENDER = CustomerGender(1L, CustomerGenderParams.MALE.value)
        private val BIRTH_DATE = LocalDate.of(1999, 8, 31)
    }

    fun create(): Customer {
        return Customer(ID, NAME, CPF, GENDER, EMAIL, BIRTH_DATE)
    }
}
