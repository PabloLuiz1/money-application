package br.edu.pablo.usecase.exception

class InvalidCustomerGenderException(
    override val message: String = "Gênero inválido"
) : ServiceErrorException(message)
