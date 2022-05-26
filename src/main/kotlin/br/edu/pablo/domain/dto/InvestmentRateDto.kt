package br.edu.pablo.domain.dto

class InvestmentRateDto(
    val data: String,
    val valor: Double
) {
    override fun toString(): String {
        return "{ \"data:\" \"".plus(data).plus("\", \"valor:\" ").plus(valor).plus(" }")
    }
}
