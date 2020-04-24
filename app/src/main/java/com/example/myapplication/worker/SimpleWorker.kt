package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication.fragment.WorkManagerFragment

class SimpleWorker(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {

        val userNameValue = inputData.getString(WorkManagerFragment.USER_NAME)
        Log.i(TAG, "Username : $userNameValue")

        for (i in 1..5) {
            Thread.sleep(1000)
            Log.i(TAG, "---> $i")
        }
        return Result.success()
    }

    companion object {
        val TAG = SimpleWorker::class.java.simpleName
    }

}