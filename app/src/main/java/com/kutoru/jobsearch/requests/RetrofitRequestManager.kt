package com.kutoru.jobsearch.requests

import com.kutoru.jobsearch.models.ApiData
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

    override suspend fun getApiData(): Result<ApiData> {
        return kotlin.runCatching {
            val response = appApi.getApiData()

            if (!response.isSuccessful) {
                throw Exception("Invalid API response with code: ${response.code()}")
            }

            return@runCatching response.body()!!
        }
    }
}
