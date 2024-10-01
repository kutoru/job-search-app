package com.kutoru.jobsearch.di

import com.kutoru.jobsearch.favorite.FavoriteComponent
import com.kutoru.jobsearch.search.SearchComponent
import dagger.Module

@Module(subcomponents = [SearchComponent::class, FavoriteComponent::class])
interface AppSubcomponents
