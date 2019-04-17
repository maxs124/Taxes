package com.mshmelev.taxes.utils

import com.mshmelev.taxes.db.domain.BudgetData
import com.mshmelev.taxes.db.domain.IrsData


fun calculator(zipTax: List<IrsData>, budget: MutableList<BudgetData>): List<CalculatedBudget> {
    val totalFundedBudgetRatio = 779000000.0 / budget.sumByDouble { dept -> dept.budgetAmount.toDouble() }

    val totalTax = 2855000000.0

    val totalZipTax = zipTax.sumByDouble { tax -> (tax.numReturns * tax.tax) }
    val totalZipReturns = zipTax.sumBy { it.numReturns }
    val myTax = totalZipTax / totalZipReturns
    val myTaxPct = myTax / totalTax

    return budget.map { dept ->
        CalculatedBudget(
                department = dept.department,
                taxesPaid = ((dept.budgetAmount * totalFundedBudgetRatio) * myTaxPct))
    }


}