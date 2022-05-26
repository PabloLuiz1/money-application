package br.edu.pablo.usecase.exception

class ApiIntegrationErrorException(override val message: String = "Erro na integração com a API") : ServiceErrorException(message)
