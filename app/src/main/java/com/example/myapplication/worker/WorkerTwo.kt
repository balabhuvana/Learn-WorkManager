package com.example.myapplication.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class WorkerTwo(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        Thread.sleep(20000)
        val dataOutput = workDataOf(WORKER_TWO to 5)
        return Result.success(dataOutput)
    }

    companion object {
        val WORKER_TWO = "worker_two"
    }
}