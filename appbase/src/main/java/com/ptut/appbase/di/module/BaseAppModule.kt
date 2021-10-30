package com.ptut.appbase.di.module

import android.app.Application
import android.content.Context
import com.ptut.appbase.di.module.BaseAppModule.Provider
import com.ptut.appbase.di.viewmodel.ViewModelFactoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        Provider::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class
    ]
)
abstract class BaseAppModule {

    @Module
    object Provider {

    }

}

