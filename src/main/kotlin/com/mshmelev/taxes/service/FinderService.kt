package com.mshmelev.taxes.service

import com.mshmelev.taxes.db.domain.BudgetData
import com.mshmelev.taxes.db.repository.BudgetDataRepository
import com.mshmelev.taxes.db.repository.IrsDataRepository
import org.springframework.stereotype.Service

@Service
class FinderService(private val irsDataRepository: IrsDataRepository, private val budgetRepository: BudgetDataRepository) {

    fun getTaxPaidByStateAndZip(state: String, zipCode: String): Double =
            irsDataRepository.findAllByStateAndZipCode(state, zipCode).sumByDouble { it.tax }

    fun getTaxPaidByState(state: String): Double =
            irsDataRepository.findAllByState(state).sumByDouble { it.tax }

    fun getTaxPaidByZipCode(zipCode: String): Double =
            irsDataRepository.findAllByZipCode(zipCode).sumByDouble { it.tax }

    fun getTaxForAll(): Double =
            irsDataRepository.findAll().sumByDouble { it.tax }

    fun getBudget(): MutableList<BudgetData> =
            budgetRepository.findAll()

}