package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateStatusRepository
import br.edu.pablo.factory.InvestmentRateStatusFactory
import br.edu.pablo.usecase.constant.ONCE
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class InvestmentRateStatusServiceTest {
    private val investmentRateStatusRepositoryMock = mockk<InvestmentRateStatusRepository>()
    private val investmentRateStatusService = InvestmentRateStatusService(investmentRateStatusRepositoryMock)
    private val investmentRateStatusUpdated = InvestmentRateStatusFactory().createUpdated()

    @Test
    fun mustFindByDescriptionWithSuccess() {
        every { investmentRateStatusRepositoryMock.findInvestmentRateStatusByDescription(any()) } returns Optional.of(investmentRateStatusUpdated)

        investmentRateStatusService.findByDescription(investmentRateStatusUpdated.description)

        verify(exactly = ONCE) { investmentRateStatusRepositoryMock.findInvestmentRateStatusByDescription(any()) }
    }

    @Test
    fun mustFindByDescriptionWithError() {
        every { investmentRateStatusRepositoryMock.findInvestmentRateStatusByDescription(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateStatusService.findByDescription(investmentRateStatusUpdated.description) }

        verify(exactly = ONCE) { investmentRateStatusRepositoryMock.findInvestmentRateStatusByDescription(any()) }
    }
}
