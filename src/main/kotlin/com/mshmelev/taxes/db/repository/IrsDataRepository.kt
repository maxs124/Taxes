package com.mshmelev.taxes.db.repository

import com.mshmelev.taxes.db.domain.IrsData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IrsDataRepository: JpaRepository<IrsData, Long> {
    fun findAllByState(state: String): List<IrsData>
    fun findAllByZipCode(zipcode: String): List<IrsData>
    fun findAllByStateAndZipCode(state: String, zipcode: String): List<IrsData>
}