package com.kutoru.jobsearch.models

import com.google.gson.annotations.SerializedName

data class Offer(
    val id: OfferId?,
    val title: String,
    val button: OfferButton?,
    val link: String,
)

data class OfferButton(
    val text: String,
)

enum class OfferId(val value: String) {
    @SerializedName("near_vacancies")
    NearVacancies("near_vacancies"),
    @SerializedName("level_up_resume")
    LevelUpResume("level_up_resume"),
    @SerializedName("temporary_job")
    TemporaryJob("temporary_job"),
}
