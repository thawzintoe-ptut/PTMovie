package com.ptut.ptmovie.initializer

import android.content.Context
import androidx.startup.Initializer

class StartupInitializer : Initializer<Unit> {
    override fun create(context: Context) {
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(
            WorkManagerStartupInitializer::class.java,
            SyncMoviesInitializer::class.java
        )
    }
}
