package com.elements.data

private const val JSON = "JSON"
private const val CSV = "CSV"
private const val DOT = "."

object DataLoaderFactory {
    fun createDataLoader(dataFile: String?): DataLoader {
        if (dataFile.isNullOrEmpty()) {
            throw IllegalArgumentException("Data file name cannot be null or empty")
        } else {
            return when (dataFile.uppercase().substringAfterLast(DOT)) {
                JSON -> JsonDataLoader(dataFile)
                CSV -> CsvDataLoader(dataFile)
                else -> throw IllegalArgumentException("Unsupported data file format: $dataFile")
            }
        }
    }
}
