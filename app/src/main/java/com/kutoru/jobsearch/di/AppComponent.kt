package com.kutoru.jobsearch.di

import android.content.Context
import com.kutoru.jobsearch.main.FavoriteManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun favoriteManager(): FavoriteManager

    fun searchComponent(): SearchComponent.Factory
    fun favoriteComponent(): FavoriteComponent.Factory
}
