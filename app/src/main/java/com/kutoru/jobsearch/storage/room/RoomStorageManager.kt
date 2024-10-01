package com.kutoru.jobsearch.storage.room

import android.content.Context
import androidx.room.Room
import com.kutoru.jobsearch.models.VacancyFavorite
import com.kutoru.jobsearch.storage.StorageManager
import javax.inject.Inject

class RoomStorageManager @Inject constructor(context: Context) : StorageManager {
    private val db = Room.databaseBuilder(
        context, AppDatabase::class.java, "job_search",
    ).build()

    override suspend fun getAllVacFavs(): Result<List<VacancyFavorite>> {
        return kotlin.runCatching {
            db.vacancyFavoriteDao().getAll()
        }
    }

    override suspend fun setVacFav(vacancyFavorite: VacancyFavorite): Result<Unit> {
        return kotlin.runCatching {
            db.vacancyFavoriteDao().setFavorite(vacancyFavorite)
        }
    }

    override suspend fun getVacFav(id: String): Result<VacancyFavorite> {
        return kotlin.runCatching {
            db.vacancyFavoriteDao().getById(id)
        }
    }

    override suspend fun deleteAllVacFavs(): Result<Unit> {
        return kotlin.runCatching {
            db.vacancyFavoriteDao().deleteAll()
        }
    }
}
