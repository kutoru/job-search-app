package com.kutoru.jobsearch.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kutoru.jobsearch.models.VacancyFavorite

@Database(entities = [VacancyFavorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyFavoriteDao(): VacancyFavoriteDao
}
