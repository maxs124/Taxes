package com.mshmelev.taxes.utils

import com.mshmelev.taxes.db.domain.BudgetData

fun calculator(taxPaid: Double, budget: MutableList<BudgetData>): List<CalculatedBudget> =
        budget.map {
            CalculatedBudget(department = it.department, budgetAmount = it.budgetAmount / 2)
        }