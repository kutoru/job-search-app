package com.kutoru.jobsearch.requests

import com.kutoru.jobsearch.models.ApiData
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.Vacancy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitRequestManager @Inject constructor() : RequestManager {
    private val baseUrl = "https://drive.usercontent.google.com/";
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val appApi = retrofit.create(RetrofitAppApi::class.java);

    private suspend fun getApiData(): ApiData {
        val response = appApi.getApiData()

        if (!response.isSuccessful) {
            throw Exception("Invalid API response with code: ${response.code()}")
        }

        return response.body()!!
    }

    override suspend fun getOffers(): Result<List<Offer>> {
        return kotlin.runCatching {
            val data = getApiData()
            return@runCatching data.offers
        }
    }

    override suspend fun getVacancies(limit: Int?): Result<List<Vacancy>> {
        return kotlin.runCatching {
            val data = getApiData()

            return@runCatching if (limit != null) {
                data.vacancies.subList(0, limit)
            } else {
                data.vacancies
            }
        }
    }
}
