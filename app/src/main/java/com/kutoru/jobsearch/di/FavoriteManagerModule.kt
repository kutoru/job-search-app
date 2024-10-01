package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.favorite_manager.FavoriteContainer
import com.kutoru.jobsearch.favorite_manager.FavoriteManager
import com.kutoru.jobsearch.favorite_manager.FavoriteUpdater
import dagger.Binds
import dagger.Module

@Module
interface FavoriteManagerModule {
    @Binds
    fun provideFavoriteContainer(favoriteContainer: FavoriteManager): FavoriteContainer
    @Binds
    fun provideFavoriteUpdater(favoriteUpdater: FavoriteManager): FavoriteUpdater
}
