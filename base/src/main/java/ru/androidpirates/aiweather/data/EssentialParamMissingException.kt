package ru.androidpirates.aiweather.data

class EssentialParamMissingException(missingParams: List<String>, rawObject: Any)
    : RuntimeException("The params: $missingParams are missing in received object: $rawObject")