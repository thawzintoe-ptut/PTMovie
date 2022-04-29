package com.ptut.appbase.di.module

import com.ptut.appbase.di.viewmodel.ViewModelFactoryModule
import dagger.Module

@Module(
    includes = [
        ViewModelFactoryModule::class,
        RepositoryModule::class
    ]
)
abstract class BaseAppModule
