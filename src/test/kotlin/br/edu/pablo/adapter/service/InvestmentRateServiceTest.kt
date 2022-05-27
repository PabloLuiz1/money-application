package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.InvestmentRateRepository
import br.edu.pablo.factory.InvestmentRateFactory
import br.edu.pablo.factory.InvestmentRateStatusFactory
import br.edu.pablo.usecase.constant.NEVER
import br.edu.pablo.usecase.constant.ONCE
import br.edu.pablo.usecase.constant.TWICE
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
    private val investmentRateStatusServiceMock = mockk<InvestmentRateStatusService>()
    private val investmentRateService = InvestmentRateService(investmentRateRepositoryMock, investmentRateStatusServiceMock)
    private val investmentRateMock = InvestmentRateFactory().createWithDefaultPercentage()
    private val investmentRateOutdatedMock = InvestmentRateFactory().createOutdatedRate()
    private val investmentRateStatusUpdatedMock = InvestmentRateStatusFactory().createUpdated()
    private val investmentRateStatusOutdatedMock = InvestmentRateStatusFactory().createOutdated()

    @Test
    fun mustSaveInvestmentRateWithSuccess() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusUpdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateMock)
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        investmentRateService.save(investmentRateMock)

        verify(exactly = TWICE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = TWICE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotSaveInvestmentRateWithErrorOnFindRateStatus() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.empty()
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateMock)
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        Assert.assertThrows(NoSuchElementException::class.java) { investmentRateService.save(investmentRateMock) }

        verify(exactly = ONCE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotSaveInvestmentRateWithErrorOnListInvestmentRateToUpdate() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusUpdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } throws NullPointerException()
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.save(investmentRateMock) }

        verify(exactly = TWICE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
    }

    @Test
    fun mustNotSaveInvestmentRateWithError() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusUpdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateMock)
        every { investmentRateRepositoryMock.save(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.save(investmentRateMock) }

        verify(exactly = TWICE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
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
    fun mustUpdateToOutdatedStatusWithSuccess() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusOutdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateOutdatedMock)
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateOutdatedMock

        investmentRateService.updateToOutdatedStatus()

        verify(exactly = ONCE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdatedStatusWithSuccess() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusUpdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf()
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateMock

        investmentRateService.updateToOutdatedStatus()

        verify(exactly = ONCE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdatedStatusWithErrorOnFindByDescription() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } throws NullPointerException()
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateOutdatedMock)
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateOutdatedMock

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.updateToOutdatedStatus() }

        verify(exactly = ONCE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdatedStatusWithErrorOnFindRateOnRepository() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusOutdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } throws NullPointerException()
        every { investmentRateRepositoryMock.save(any()) } returns investmentRateOutdatedMock

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.updateToOutdatedStatus() }

        verify(exactly = ONCE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = NEVER) { investmentRateRepositoryMock.save(any()) }
    }

    @Test
    fun mustNotUpdateToOutdateStatusWithErrorOnRateRepositorySave() {
        every { investmentRateStatusServiceMock.findByDescription(any()) } returns Optional.of(investmentRateStatusOutdatedMock)
        every { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) } returns mutableListOf(investmentRateOutdatedMock)
        every { investmentRateRepositoryMock.save(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { investmentRateService.updateToOutdatedStatus() }

        verify(exactly = ONCE) { investmentRateStatusServiceMock.findByDescription(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.findInvestmentRateByUpdatedDateBefore(any()) }
        verify(exactly = ONCE) { investmentRateRepositoryMock.save(any()) }
    }
}
