package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.storage.DBStorage
import com.kutoru.jobsearch.storage.RoomDBStorage
import dagger.Binds
import dagger.Module

@Module
interface DBStorageModule {
    @Binds
    fun provideDBStorage(dbStorage: RoomDBStorage): DBStorage
}
