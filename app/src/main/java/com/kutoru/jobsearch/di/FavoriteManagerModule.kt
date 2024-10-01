package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.main.FavoriteContainer
import com.kutoru.jobsearch.main.FavoriteManager
import com.kutoru.jobsearch.main.FavoriteUpdater
import dagger.Binds
import dagger.Module

@Module
interface FavoriteManagerModule {
    @Binds
    fun provideFavoriteContainer(favoriteContainer: FavoriteManager): FavoriteContainer
    @Binds
    fun provideFavoriteUpdater(favoriteUpdater: FavoriteManager): FavoriteUpdater
}
