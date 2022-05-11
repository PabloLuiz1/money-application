package br.edu.pablo.domain.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val cpf: String,

    @ManyToOne
    val gender: CustomerGender,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val birthDate: LocalDate
)
