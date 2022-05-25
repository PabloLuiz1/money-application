package br.edu.pablo.factory

import br.edu.pablo.domain.entity.CustomerAccount

class CustomerAccountFactory {

    companion object {
        private const val ID = 1L
        private const val BALANCE = 123.45
        private val CUSTOMER = CustomerFactory().create()
    }

    fun create(): CustomerAccount {
        return CustomerAccount(ID, CUSTOMER, BALANCE)
    }
}
