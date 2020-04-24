package com.example.myapplication.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class WorkerThree(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        Thread.sleep(5000)
        val dataOutput = workDataOf(WORKER_THREE to 30)
        return Result.success(dataOutput)
    }

    companion object {
        const val WORKER_THREE = "worker_three"
    }
}