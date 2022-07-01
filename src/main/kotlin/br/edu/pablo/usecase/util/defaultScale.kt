package br.edu.pablo.usecase.util

import br.edu.pablo.usecase.constant.BRL_SCALE
import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.defaultScale(): BigDecimal {
    return this.setScale(BRL_SCALE, RoundingMode.HALF_EVEN)
}
