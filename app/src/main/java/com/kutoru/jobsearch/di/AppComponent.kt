package com.kutoru.jobsearch.di

import android.content.Context
import com.kutoru.jobsearch.main.FavoriteContainer
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RequestManagerModule::class, DBStorageModule::class, FavoriteManagerModule::class, AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun favoriteContainer(): FavoriteContainer

    fun searchComponent(): SearchComponent.Factory
    fun favoriteComponent(): FavoriteComponent.Factory
}
