package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.requests.RequestManager
import com.kutoru.jobsearch.requests.RetrofitRequestManager
import dagger.Binds
import dagger.Module

@Module
interface RequestManagerModule {
    @Binds
    fun provideRequestManager(requestManager: RetrofitRequestManager): RequestManager
}
