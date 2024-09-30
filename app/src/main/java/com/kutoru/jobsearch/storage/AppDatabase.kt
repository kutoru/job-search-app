package com.kutoru.jobsearch.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kutoru.jobsearch.models.Offer

@Database(entities = [Offer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao
}
