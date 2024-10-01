package com.kutoru.jobsearch

import android.app.Application
import com.kutoru.jobsearch.di.AppComponent
import com.kutoru.jobsearch.di.DaggerAppComponent

class JobSearchApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
