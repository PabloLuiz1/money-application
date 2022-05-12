package br.edu.pablo.adapter.repository

import br.edu.pablo.domain.entity.InvestmentRateStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface InvestmentRateStatusRepository : JpaRepository<InvestmentRateStatus, Long> {

    fun findInvestmentRateStatusByDescription(description: String): Optional<InvestmentRateStatus>
}
