package com.ptut.ptmovie.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.work.*
import com.ptut.ptmovie.feature.startup.StartupWorker
import com.ptut.ptmovie.feature.startup.WorkManagerConstants
import org.threeten.bp.Duration
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SyncMoviesInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        try {
            val request =
                PeriodicWorkRequestBuilder<StartupWorker>(Duration.ofHours(1).seconds, TimeUnit.SECONDS)
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )
                    .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WorkManagerConstants.SYNC_MOVIES,
                ExistingPeriodicWorkPolicy.REPLACE,
                request
            )
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
