package com.kutoru.jobsearch.models

data class VacancyResponse(
    var vacancies: List<Vacancy>,
    val total: Int,
)
