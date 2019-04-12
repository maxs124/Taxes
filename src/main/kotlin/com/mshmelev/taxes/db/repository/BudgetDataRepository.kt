package com.mshmelev.taxes.db.repository

import com.mshmelev.taxes.db.domain.BudgetData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BudgetDataRepository: JpaRepository<BudgetData, Long>