package com.kutoru.jobsearch.requests

import dagger.Binds
import dagger.Module

@Module
interface RequestManagerModule {
    @Binds
    fun provideRequestManager(requestManager: RetrofitRequestManager): RequestManager
}
