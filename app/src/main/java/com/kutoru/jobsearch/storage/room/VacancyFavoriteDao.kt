package com.kutoru.jobsearch.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kutoru.jobsearch.models.VacancyFavorite

@Dao
interface VacancyFavoriteDao {
    @Query("SELECT * FROM vacancyfavorite")
    suspend fun getAll(): List<VacancyFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(vacancyFavorite: VacancyFavorite)

    @Query("SELECT * FROM vacancyfavorite WHERE id = :id")
    suspend fun getById(id: String): VacancyFavorite

    @Query("DELETE FROM vacancyfavorite")
    suspend fun deleteAll()
}
