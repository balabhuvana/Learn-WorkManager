package com.example.myapplication.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class DependantWork(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        val workerOneData = inputData.getInt(WorkerOne.WORKER_ONE, 0)
        val workerTwoData = inputData.getInt(WorkerTwo.WORKER_TWO, 0)
        val workerThreeData = inputData.getInt(WorkerThree.WORKER_THREE, 0)
        val combineData = workerOneData + workerTwoData + workerThreeData
        val dataOutput = workDataOf(DependantWork to combineData)
        return Result.success(dataOutput)
    }

    companion object {
        val DependantWork = "dependant_work"
    }
}