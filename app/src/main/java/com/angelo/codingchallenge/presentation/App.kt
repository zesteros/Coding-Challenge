package com.angelo.codingchallenge.presentation

import android.app.Application
import com.angelo.codingchallenge.BuildConfig
import com.angelo.codingchallenge.presentation.di.Injector
import com.angelo.codingchallenge.presentation.di.core.*
import com.angelo.codingchallenge.presentation.di.page.PageSubComponent
import dagger.internal.DaggerCollections

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .aPIModule(APIModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule()).build()

    }

    override fun createPageSubcomponent(): PageSubComponent = appComponent.pageSubComponent().create()
}