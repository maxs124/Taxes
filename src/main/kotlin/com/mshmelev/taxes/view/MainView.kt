package com.mshmelev.taxes.view

import com.mshmelev.taxes.service.FinderService
import com.mshmelev.taxes.utils.calculator
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.annotation.SpringComponent
import com.vaadin.flow.spring.annotation.UIScope


@UIScope
@SpringComponent
@Route
class MainView(private val finderService: FinderService) : VerticalLayout() {
    private fun initialize() {
        val message = H1()
        val textField = TextField("Zip Code", "Zip Code")
//      textField.addValueChangeListener { event -> message.text = "${finderService.getTaxPaidByZipCode(event.value)} Dollars" }

        textField.addValueChangeListener { event ->
            val paidTax = finderService.getTaxPaidByZipCode(event.value)
            val budget = finderService.getBudget()

            calculator(paidTax, budget)
        }


        add(textField, message)
    }

    init {
        initialize()
    }
}