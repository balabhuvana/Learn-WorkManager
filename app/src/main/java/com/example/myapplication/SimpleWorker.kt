package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        for (i in 1..5) {
            Thread.sleep(1000)
            Log.i(TAG, "---> $i")
        }
        return Result.success()
    }

    companion object {
        val TAG = SimpleWorker.javaClass.simpleName
    }

}