package com.mshmelev.taxes.db.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
@Table(name = "irs_data")
data class IrsData(
        @NotNull
        @Size(max = 2)
        @Column(name = "state", length = 2, nullable = false)
        var state: String = "",

        @NotNull
        @Size(max = 5)
        @Column(name = "zip_code", length = 5, nullable = false)
        var zipCode: String = "",

        @NotNull
        @Column(name = "agi_stub", nullable = false)
        var agiStub: Int = -1,

        @NotNull
        @Column(name = "num_returns", nullable = false)
        var numReturns: Int = -1,

        @NotNull
        @Column(name = "agi", nullable = false)
        var agi: Double = 0.0,

        @NotNull
        @Column(name = "tax", nullable = false)
        var tax: Double = 0.0,

        @JsonIgnore
        @Version
        private var version: Long = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1L)