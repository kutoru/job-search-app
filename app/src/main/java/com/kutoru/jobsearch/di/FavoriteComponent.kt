package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.favorite.FavoriteFragment
import dagger.Subcomponent

@Subcomponent
interface FavoriteComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)
}
