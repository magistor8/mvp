package com.magistor8.translator.di

import com.magistor8.translator.view.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(viewModel: MainViewModel)
}