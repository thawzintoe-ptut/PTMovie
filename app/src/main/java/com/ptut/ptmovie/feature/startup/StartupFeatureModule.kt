package com.ptut.ptmovie.feature.startup

import com.ptut.ptmovie.di.ChildWorkerFactory
import com.ptut.ptmovie.di.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartupFeatureModule {

    @Binds
    @IntoMap
    @WorkerKey(value = StartupWorker::class)
    abstract fun syncWorker(factory: StartupWorker.Factory): ChildWorkerFactory
}
