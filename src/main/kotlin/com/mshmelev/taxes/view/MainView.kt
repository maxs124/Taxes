package com.mshmelev.taxes.view

import com.mshmelev.taxes.db.repository.IrsDataRepository
import com.mshmelev.taxes.service.FinderService
import com.mshmelev.taxes.utils.CalculatedBudget
import com.mshmelev.taxes.utils.calculator
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.annotation.SpringComponent
import com.vaadin.flow.spring.annotation.UIScope


@UIScope
@SpringComponent
@Route
class MainView(private val finderService: FinderService,
               private val irsDataRepository: IrsDataRepository) : VerticalLayout() {
    private fun initialize() {
        val errorText = Label()
        val grid = Grid(CalculatedBudget::class.java)
        val textField = TextField("Zip Code", "Zip Code")
        textField.width = "100%"
//      textField.addValueChangeListener { event -> message.text = "${finderService.getTaxPaidByZipCode(event.value)} Dollars" }

        textField.addValueChangeListener { event ->
            val paidTax = finderService.getTaxPaidByZipCode(event.value)
            val budget = finderService.getBudget()

            if (paidTax.toInt() == 0 || event.value.toInt() == 0) {
                errorText.text = "ERROR: Enter a Valid Zip Code"
            } else {
                val allTaxes = irsDataRepository.findAll()
                val zipTaxes = irsDataRepository.findAllByZipCode(event.value)
                val calculatedTax: List<CalculatedBudget> = calculator(allTaxes, zipTaxes, budget)
                errorText.text = "${calculator(allTaxes, zipTaxes, budget)}"
                grid.setSizeFull()
                grid.setItems(calculatedTax)

            }

        }
        add(textField, errorText, grid)
    }

    init {
        initialize()
    }
}
