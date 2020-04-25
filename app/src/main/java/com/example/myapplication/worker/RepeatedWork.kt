package com.example.myapplication.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class RepeatedWork(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        Log.i(TAG, "Hey am doing the repeated work")
        return Result.success()
    }

    companion object {
        val TAG = RepeatedWork::class.java.simpleName
    }

}