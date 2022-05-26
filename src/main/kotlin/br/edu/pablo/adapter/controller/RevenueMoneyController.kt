package br.edu.pablo.adapter.controller

import br.edu.pablo.adapter.service.RevenueMoneyService
import br.edu.pablo.domain.dto.CustomerDto
import br.edu.pablo.domain.entity.RevenueMoney
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/revenue-money")
class RevenueMoneyController(
    val revenueMoneyService: RevenueMoneyService
) {

    @GetMapping("/all")
    fun findAll(): ResponseEntity<MutableList<RevenueMoney>> {
        return ResponseEntity.ok().body(revenueMoneyService.findAll())
    }

    @PostMapping("/customer")
    fun findByCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<MutableList<RevenueMoney>> {
        return ResponseEntity.ok().body(revenueMoneyService.findByCustomer(customerDto.id))
    }
}
