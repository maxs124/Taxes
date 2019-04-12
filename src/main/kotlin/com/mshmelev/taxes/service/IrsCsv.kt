package com.mshmelev.taxes.service

import au.com.bytecode.opencsv.CSVParser
import au.com.bytecode.opencsv.CSVReader
import com.mshmelev.taxes.db.domain.IrsData
import com.mshmelev.taxes.db.repository.IrsDataRepository
import com.mshmelev.taxes.utils.parseAsDouble
import com.mshmelev.taxes.utils.parseAsInt
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import javax.annotation.PostConstruct

@Service
class IrsCsv(private val irsDataRepository: IrsDataRepository) {

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

                val irsData = IrsData(
                        state = record[1],
                        zipCode = record[2],
                        agiStub = record[3].parseAsInt(),
                        numReturns = record[4].parseAsInt(),
                        agi = record[18].parseAsDouble(),
                        tax = record[136].parseAsDouble())

                //println(irsDataRepository.save(irsData))
                irsDataRepository.save(irsData)
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
        println("$counter records loaded in ${System.currentTimeMillis() - startTime}ms (Irs Data)")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(IrsCsv::class.java)
        private const val CLASS_LOADER_GEOLOCATE_FILE_PATH = "data/16zpallagi.csv"
    }
}


