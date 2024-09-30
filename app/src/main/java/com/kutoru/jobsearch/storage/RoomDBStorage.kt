package com.kutoru.jobsearch.storage

import android.content.Context
import androidx.room.Room
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy
import javax.inject.Inject

class RoomDBStorage @Inject constructor(context: Context) : DBStorage {
    private val db = Room.databaseBuilder(
        context, AppDatabase::class.java, "job_search",
    ).build()

    override suspend fun addVacancies(vacancies: List<Vacancy>): Result<Unit> {
        // do this
        return Result.success(Unit)
    }

    override suspend fun addOffers(offers: List<Offer>): Result<Unit> {
        return kotlin.runCatching {
            db.offerDao().insertAll(*offers.toTypedArray())
        }
    }

    override suspend fun getVacancies(limit: Int): Result<List<Vacancy>> {
        // do this
        return Result.success(listOf())
    }

    override suspend fun getOffers(): Result<List<Offer>> {
        return kotlin.runCatching {
            db.offerDao().getAll()
        }
    }

    override suspend fun setVacancyAsFavorite(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun unsetVacancyAsFavorite(id: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun clear(): Result<Unit> {
        return kotlin.runCatching {
            db.offerDao().deleteAll()
//            db.vacancyDao().deleteAll()
        }
    }
}
