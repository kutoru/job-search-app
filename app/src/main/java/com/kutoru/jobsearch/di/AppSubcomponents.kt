package com.kutoru.jobsearch.di

import dagger.Module

@Module(subcomponents = [SearchComponent::class, FavoriteComponent::class])
interface AppSubcomponents
