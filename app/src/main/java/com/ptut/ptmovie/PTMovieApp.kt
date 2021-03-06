package com.ptut.ptmovie

import android.app.Application
import androidx.startup.AppInitializer
import androidx.viewbinding.BuildConfig
import com.jakewharton.threetenabp.AndroidThreeTen
import com.ptut.appbase.di.AppInjector
import com.ptut.ptmovie.di.DaggerAppComponent
import com.ptut.ptmovie.initializer.StartupInitializer
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class PTMovieApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        AppInjector.initAutoInjection(this)

//        AppInitializer.getInstance(this)
//            .initializeComponent(StartupInitializer::class.java)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
