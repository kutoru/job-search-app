package com.kutoru.jobsearch.di

import android.content.Context
import com.kutoru.jobsearch.favorite.FavoriteComponent
import com.kutoru.jobsearch.favorite_manager.FavoriteContainer
import com.kutoru.jobsearch.favorite_manager.FavoriteManagerModule
import com.kutoru.jobsearch.requests.RequestManagerModule
import com.kutoru.jobsearch.search.SearchComponent
import com.kutoru.jobsearch.storage.StorageManagerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RequestManagerModule::class, StorageManagerModule::class, FavoriteManagerModule::class, AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun favoriteContainer(): FavoriteContainer

    fun searchComponent(): SearchComponent.Factory
    fun favoriteComponent(): FavoriteComponent.Factory
}
