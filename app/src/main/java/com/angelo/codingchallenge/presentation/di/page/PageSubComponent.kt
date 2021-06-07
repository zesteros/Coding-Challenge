package com.angelo.codingchallenge.presentation.di.page

import com.angelo.codingchallenge.presentation.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [PageModule::class])
interface PageSubComponent {
    fun inject(mainActivity: MainActivity)
    @Subcomponent.Factory
    interface Factory {
        fun create(): PageSubComponent
    }
}