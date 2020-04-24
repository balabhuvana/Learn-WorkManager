package com.example.myapplication.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class StartWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        Log.i(TAG, "---> doWork() before sleep")
        Thread.sleep(30000)
        Log.i(TAG, "---> doWork() after sleep")
        return Result.success()
    }

    override fun onStopped() {
        Log.i(TAG, "---> onStopped()")
        super.onStopped()
    }

    companion object {
        val TAG = StartWorker::class.java.simpleName
    }

}