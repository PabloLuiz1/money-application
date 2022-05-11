package br.edu.pablo.usecase.exception

class InvalidInvestmentRateException(
    override val message: String = "Status da Taxa de Investimento inválido"
) : ServiceErrorException(message)
