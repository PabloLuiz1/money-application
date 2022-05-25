package br.edu.pablo.factory

import br.edu.pablo.domain.dto.CustomerDto

class CustomerDtoFactory {
    companion object {
        private const val ID = 1L
        private const val NAME = "Pablo Luiz Ribeiro"
        private const val CPF = "12345678900"
    }

    fun create(): CustomerDto {
        return CustomerDto(ID, NAME, CPF)
    }
}
