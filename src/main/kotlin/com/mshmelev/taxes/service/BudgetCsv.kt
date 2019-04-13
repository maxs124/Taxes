package com.mshmelev.taxes.service

import au.com.bytecode.opencsv.CSVParser
import au.com.bytecode.opencsv.CSVReader
import com.mshmelev.taxes.db.domain.BudgetData
import com.mshmelev.taxes.db.repository.BudgetDataRepository
import com.mshmelev.taxes.utils.parseAsLong
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import javax.annotation.PostConstruct

@Service
class BudgetCsv(private val budgetDataRepository: BudgetDataRepository) {

    @PostConstruct
    private fun initialize() {
        loadTable(CLASS_LOADER_GEOLOCATE_FILE_PATH)
    }

    private fun loadTable(resourceName: String) {
        var csvReader: CSVReader? = null
        val classLoader: ClassLoader
        var inputStream: InputStream? = null
        var isReader: InputStreamReader? = null
        var record: Array<String>?
        val startTime = System.currentTimeMillis()
        var counter = 0


        try {
            classLoader = javaClass.classLoader
            inputStream = classLoader.getResourceAsStream(resourceName)
            isReader = InputStreamReader(inputStream!!, "UTF8")

            csvReader = CSVReader(isReader, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 1, CSVParser.DEFAULT_STRICT_QUOTES)

            // Loop through the file
            while (true) {
                // Read the next CSV row
                record = csvReader.readNext()
                if (record == null || record.isEmpty()) {
                    // No more rows? We're done then...
                    break
                }

                val budgetData = BudgetData(
                        department = record[0],
                        budgetAmount = record[1].parseAsLong())

//                println("${budgetData.department} = ${budgetData.budgetAmount}")
                budgetDataRepository.save(budgetData)
                counter++
            }
        } catch (e: UnsupportedEncodingException) {
            logger.error("UnsupportedEncodingException: " + e.message)
        } catch (e: IOException) {
            logger.error("IOException: " + e.message)
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close()
                } catch (e: IOException) {
                    // Ignore this
                }
            }
            if (isReader != null) {
                try {
                    isReader.close()
                } catch (e: IOException) {
                    // Ignore this
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
// Ignore this
                }

            }
        }
        println("$counter records loaded in ${System.currentTimeMillis() - startTime}ms(Budget Data)")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BudgetCsv::class.java)
        private const val CLASS_LOADER_GEOLOCATE_FILE_PATH = "data/budget2018.csv"
    }
}


