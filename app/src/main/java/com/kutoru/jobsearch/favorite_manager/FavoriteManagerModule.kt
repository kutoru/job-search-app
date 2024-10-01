package com.kutoru.jobsearch.favorite_manager

import dagger.Binds
import dagger.Module

@Module
interface FavoriteManagerModule {
    @Binds
    fun provideFavoriteContainer(favoriteContainer: FavoriteManager): FavoriteContainer
    @Binds
    fun provideFavoriteUpdater(favoriteUpdater: FavoriteManager): FavoriteUpdater
}
