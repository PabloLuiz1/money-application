package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateRepository
import br.edu.pablo.factory.InvestmentRateFactory
import br.edu.pablo.factory.InvestmentRateStatusFactory
import br.edu.pablo.usecase.constant.NEVER
import br.edu.pablo.usecase.constant.ONCE
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class InvestmentRateServiceTest {
    private val investmentRateRepositoryMock = mockk<InvestmentRateRepository>()
    private val investmentRateStatusService = mockk<InvestmentRateStatusService>()
    private val investmentRateService = InvestmentRateService(investmentRateRepositoryMock, investmentRateStatusService)
    private val investmentRateMock = InvestmentRateFactory().createWithDefaultPercentage()
    private val investmentRateOutdatedMock = InvestmentRateFactory().createOutdatedRate()
    private val investmentRateStatusUpdated = InvestmentRateStatusFactory().createUpdated()
    private val investmentRateStatusOutdated = InvestmentRateStatusFactory().createOutdated()

    @Test
    fun mustGetUpdatedRateWithSuccess() {
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        investmentRateService.getUpdatedRate()

        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustGetUpdatedRateWithError() {
        every { investmentRateRepositoryMock.save(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.getUpdatedRate() }

        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustSaveInvestmentRateWithSuccess() {
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        investmentRateService.save(investmentRateMock)

        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustSaveInvestmentRateWithError() {
        every { investmentRateRepositoryMock.save(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.save(investmentRateMock) }

        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustFindAllInvestmentRateWithSuccess() {
        every { investmentRateRepositoryMock.findAll() } returns listOf(investmentRateMock)

        investmentRateService.findAll()

        verify(exactly = ONCE) { investmentRateRepositoryMock.findAll() }
    }

    @Test
    fun mustFindAllInvestmentRateWithError() {
        every { investmentRateRepositoryMock.findAll() } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.findAll() }

        verify(exactly = ONCE) { investmentRateRepositoryMock.findAll() }
    }

    @Test
    fun mustUpdateToOutdateStatusWithSuccess() {
        every { investmentRateStatusService.findByDescription(any()) } returns Optional.of(investmentRateStatusOutdated)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateOutdatedMock)
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateOutdatedMock

        investmentRateService.updateToOutdatedStatus()

        verify(exactly = ONCE) { investmentRateStatusService.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdateStatusWithSuccess() {
        every { investmentRateStatusService.findByDescription(any()) } returns Optional.of(investmentRateStatusUpdated)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf()
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        investmentRateService.updateToOutdatedStatus()

        verify(exactly = ONCE) { investmentRateStatusService.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdateStatusWithErrorOnStatusRepository() {
        every { investmentRateStatusService.findByDescription(any()) } throws NullPointerException()
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateOutdatedMock)
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateOutdatedMock

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.updateToOutdatedStatus() }

        verify(exactly = ONCE) { investmentRateStatusService.findByDescription(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdateStatusWithErrorOnRateRepositoryFindAll() {
        every { investmentRateStatusService.findByDescription(any()) } returns Optional.of(investmentRateStatusOutdated)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } throws NullPointerException()
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateOutdatedMock

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.updateToOutdatedStatus() }

        verify(exactly = ONCE) { investmentRateStatusService.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdateStatusWithErrorOnRateRepositorySave() {
        every { investmentRateStatusService.findByDescription(any()) } returns Optional.of(investmentRateStatusOutdated)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateOutdatedMock)
        every { investmentRateRepositoryMock.save(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.updateToOutdatedStatus() }

        verify(exactly = ONCE) { investmentRateStatusService.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }
}
