package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.RevenueMoneyRepository
import br.edu.pablo.factory.CustomerFactory
import br.edu.pablo.factory.RevenueMoneyFactory
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
internal class RevenueMoneyServiceTest {
    private val revenueMoneyRepositoryMock = mockk<RevenueMoneyRepository>()
    private val customerServiceMock = mockk<CustomerService>()
    private val revenueMoneyService = RevenueMoneyService(revenueMoneyRepositoryMock, customerServiceMock)
    private val revenueMoneyMock = RevenueMoneyFactory().create()
    private val customerMock = CustomerFactory().create()

    @Test
    fun mustSaveWithSuccess() {
        every { revenueMoneyRepositoryMock.save(any()) } returns revenueMoneyMock

        revenueMoneyService.save(revenueMoneyMock)

        verify(exactly = ONCE) { revenueMoneyRepositoryMock.save(any()) }
    }

    @Test
    fun mustSaveWithError() {
        every { revenueMoneyRepositoryMock.save(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { revenueMoneyService.save(revenueMoneyMock) }

        verify(exactly = ONCE) { revenueMoneyRepositoryMock.save(any()) }
    }

    @Test
    fun mustFindAllWithSuccess() {
        every { revenueMoneyRepositoryMock.findAll() } returns mutableListOf(revenueMoneyMock)

        revenueMoneyService.findAll()

        verify(exactly = ONCE) { revenueMoneyRepositoryMock.findAll() }
    }

    @Test
    fun mustFindAllWithError() {
        every { revenueMoneyRepositoryMock.findAll() } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { revenueMoneyService.findAll() }

        verify(exactly = ONCE) { revenueMoneyRepositoryMock.findAll() }
    }

    @Test
    fun mustFindByCustomerWithSuccess() {
        every { revenueMoneyRepositoryMock.findRevenueMoniesByCustomerAccount_Customer(any()) } returns mutableListOf(revenueMoneyMock)

        revenueMoneyService.findByCustomer(customerMock.id!!)

        verify(exactly = ONCE) { revenueMoneyRepositoryMock.findRevenueMoniesByCustomerAccount_Customer(any()) }
    }

    @Test
    fun mustFindByCustomerWithError() {
        every { revenueMoneyRepositoryMock.findRevenueMoniesByCustomerAccount_Customer(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { revenueMoneyService.findByCustomer(customerMock.id!!) }

        verify(exactly = ONCE) { revenueMoneyRepositoryMock.findRevenueMoniesByCustomerAccount_Customer(any()) }
    }
}
