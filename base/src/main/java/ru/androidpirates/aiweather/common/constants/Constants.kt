package ru.androidpirates.aiweather.common.constants

object Constants {
    const val NO_ID_INT = -1
    const val NO_ID_LONG = -1L

    /**
     * TODO: normally we should not use feature (in this case - 'weather') api url in base module,
     * but since we have only one feature in this project - we can define its ULR here.
     * We should refactor it as soon as we add new feature/features in this project.
      */
    const val BASE_API_URL = "https://api.openweathermap.org"
}