package com.example.myapplication.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class WorkerOne(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        Thread.sleep(10000)
        val dataOutput = workDataOf(WORKER_ONE to 10)
        return Result.success(dataOutput)
    }

    companion object {
        val WORKER_ONE = "worker_one"
    }
}