package br.edu.pablo.adapter.repository

import br.edu.pablo.domain.entity.InvestmentRate
import br.edu.pablo.domain.entity.InvestmentRateStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface InvestmentRateRepository : JpaRepository<InvestmentRate, Long> {

    fun findInvestmentRateByUpdatedDateBefore(dateTime: LocalDateTime): MutableList<InvestmentRate>
    fun findInvestmentRateByStatus(status: InvestmentRateStatus): InvestmentRate
}
