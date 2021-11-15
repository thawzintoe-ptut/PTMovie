package com.ptut.ptmovie.feature.startup

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ptut.domain.usecase.GetMoviesRequest
import com.ptut.ptmovie.di.ChildWorkerFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import javax.inject.Inject

class StartupWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    @Inject
    lateinit var getMoviesRequest: GetMoviesRequest
    override suspend fun doWork(): Result {
        return try {
            getMoviesRequest.execute("popular")
            Result.success()
        } catch (t: Throwable) {
            Result.failure()
        }
    }

    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory
}