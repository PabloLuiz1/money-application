package br.edu.pablo.adapter.service

import br.edu.pablo.adapter.repository.CustomerRepository
import br.edu.pablo.domain.entity.Customer
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun findById(id: Long): Optional<Customer> {
        return customerRepository.findById(id)
    }
}
