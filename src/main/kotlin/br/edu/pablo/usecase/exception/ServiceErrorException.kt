package br.edu.pablo.usecase.exception

import java.lang.RuntimeException

open class ServiceErrorException(message: String) : RuntimeException(message)
