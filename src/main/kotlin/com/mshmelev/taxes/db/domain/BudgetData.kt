package com.mshmelev.taxes.db.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "budget_data")
data class BudgetData(
        @NotNull
        @Size(max = 255)
        @Column(name = "department", length = 255, nullable = false)
        var department: String = "",

        @NotNull
        @Column(name = "budget_amount")
        var budgetAmount: Long = 0L,

        @JsonIgnore
        @Version
        private var version: Long = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1L)
