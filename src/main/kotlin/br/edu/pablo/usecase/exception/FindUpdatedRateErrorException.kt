package br.edu.pablo.usecase.exception

class FindUpdatedRateErrorException(override val message: String = "Erro ao buscar no banco de dados a taxa de investimento vigente") : ServiceErrorException(message)
