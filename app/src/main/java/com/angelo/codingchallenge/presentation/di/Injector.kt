package com.angelo.codingchallenge.presentation.di

import com.angelo.codingchallenge.presentation.di.page.PageSubComponent

interface Injector {
    fun createPageSubcomponent() : PageSubComponent
}