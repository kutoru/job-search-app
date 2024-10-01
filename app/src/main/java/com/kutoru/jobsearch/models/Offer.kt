package com.kutoru.jobsearch.models

data class Offer(
    val id: String?,
    val title: String,
    val button: OfferButton?,
    val link: String,
)

data class OfferButton(
    val text: String,
)
