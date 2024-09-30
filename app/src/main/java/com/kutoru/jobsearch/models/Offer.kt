package com.kutoru.jobsearch.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Offer(
    var id: String?,
    var title: String,
    @Embedded(prefix = "button_")
    var button: OfferButton?,
    var link: String,
) {
    @PrimaryKey(autoGenerate = true)
    var dbId: Int? = null
}

data class OfferButton(
    var text: String,
)
