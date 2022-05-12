package br.edu.pablo.adapter.controller

import br.edu.pablo.adapter.service.InvestmentRateService
import br.edu.pablo.factory.InvestmentRateFactory
import br.edu.pablo.usecase.constant.ONCE
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class InvestmentRateControllerTest {
    private val investmentRateServiceMock = mockk<InvestmentRateService>()
    private val investmentRateController = InvestmentRateController(investmentRateServiceMock)

    @Test
    fun mustGetInvestmentRateWithSuccess() {
        every { investmentRateServiceMock.getUpdatedRate() } returns InvestmentRateFactory().createWithDefaultPercentage()

        investmentRateController.getUpdatedRate()

        verify(exactly = ONCE) { investmentRateServiceMock.getUpdatedRate() }
    }

    @Test
    fun mustGetInvestmentRateWithError() {
        every { investmentRateServiceMock.getUpdatedRate() } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateController.getUpdatedRate() }

        verify(exactly = ONCE) { investmentRateServiceMock.getUpdatedRate() }
    }
}
