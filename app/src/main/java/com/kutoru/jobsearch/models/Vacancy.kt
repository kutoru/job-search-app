package com.kutoru.jobsearch.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vacancy(
    @PrimaryKey val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)

data class Address(
    val town: String,
    val street: String,
    val house: String,
)

data class Experience(
    val previewText: String,
    val text: String,
)

data class Salary(
    val short: String?,
    val full: String,
)
