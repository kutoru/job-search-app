package com.kutoru.jobsearch.storage

import com.kutoru.jobsearch.storage.room.RoomStorageManager
import dagger.Binds
import dagger.Module

@Module
interface StorageManagerModule {
    @Binds
    fun provideStorageManager(storageManager: RoomStorageManager): StorageManager
}
