package com.kutoru.jobsearch.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kutoru.jobsearch.models.Offer

@Dao
interface OfferDao {
    @Query("SELECT * FROM offer")
    suspend fun getAll(): List<Offer>

    @Insert
    suspend fun insertAll(vararg offers: Offer)

    @Query("DELETE FROM offer")
    suspend fun deleteAll()
}
