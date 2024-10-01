package com.kutoru.jobsearch.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyFavorite(
    @PrimaryKey var id: String,
    var isFavorite: Boolean,
)
