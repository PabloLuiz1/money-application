package br.edu.pablo.adapter.controller

import br.edu.pablo.adapter.service.RevenueMoneyService
import br.edu.pablo.factory.CustomerDtoFactory
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
internal class RevenueMoneyControllerTest {
    private val revenueMoneyServiceMock = mockk<RevenueMoneyService>()
    private val revenueMoneyController = RevenueMoneyController(revenueMoneyServiceMock)
    private val revenueMoneyMock = RevenueMoneyFactory().create()
    private val customerDtoMock = CustomerDtoFactory().create()

    @Test
    fun mustGetAllRevenuesWithSuccess() {
        every { revenueMoneyServiceMock.findAll() } returns mutableListOf(revenueMoneyMock)

        revenueMoneyController.findAll()

        verify(exactly = ONCE) { revenueMoneyServiceMock.findAll() }
    }

    @Test
    fun mustGetAllRevenuesWithError() {
        every { revenueMoneyServiceMock.findAll() } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { revenueMoneyController.findAll() }

        verify(exactly = ONCE) { revenueMoneyServiceMock.findAll() }
    }

    @Test
    fun mustFindByCustomerWithSuccess() {
        every { revenueMoneyServiceMock.findByCustomer(any()) } returns mutableListOf(revenueMoneyMock)

        revenueMoneyController.findByCustomer(customerDtoMock)

        verify(exactly = ONCE) { revenueMoneyServiceMock.findByCustomer(any()) }
    }

    @Test
    fun mustFindByCustomerWithError() {
        every { revenueMoneyServiceMock.findByCustomer(any()) } throws NullPointerException()

        Assert.assertThrows(NullPointerException::class.java) { revenueMoneyController.findByCustomer(customerDtoMock) }

        verify(exactly = ONCE) { revenueMoneyServiceMock.findByCustomer(any()) }
    }
}
