package com.example.workmanager


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.myapplication.R
import com.example.myapplication.worker.ProgressWorker
import com.example.myapplication.worker.ProgressWorker.Companion.Progress
import kotlinx.android.synthetic.main.fragment_work_manager.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class WorkManagerFragment : Fragment() {

    lateinit var uploadWorkRequest: OneTimeWorkRequest;
    lateinit var progressWorkRequest: OneTimeWorkRequest;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_manager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnStartOneTimeRequest.setOnClickListener {

            val constraints = Constraints.Builder().setRequiresBatteryNotLow(true)
                .setRequiresCharging(false)
                .build()

            val data = workDataOf(USER_NAME to "Arunkumar")

            uploadWorkRequest = OneTimeWorkRequestBuilder<SimpleWorker>().setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .addTag(SIMPLE_TAG)
                .build()
            WorkManager.getInstance(this!!.context!!).enqueue(uploadWorkRequest)

            observeSimpleWorkStatus()
        }

        btnProgressWorker.setOnClickListener {
            progressWorkRequest = OneTimeWorkRequestBuilder<ProgressWorker>()
                .build()
            WorkManager.getInstance(this!!.context!!).enqueue(progressWorkRequest)

            WorkManager.getInstance(this!!.context!!).getWorkInfoByIdLiveData(progressWorkRequest.id)
                .observe(viewLifecycleOwner,
                    Observer { workInfo: WorkInfo? ->
                        if (workInfo != null) {
                            val progressValue = workInfo.progress.getInt(Progress, 20)
                            Log.i(TAG, "Sorry Dude $progressValue")
                        }
                    })
        }
    }

    fun observeSimpleWorkStatus() {
        WorkManager.getInstance(this!!.context!!).getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(viewLifecycleOwner,
                Observer { workInfo ->
                    if (workInfo != null) {
                        if (workInfo.state == WorkInfo.State.BLOCKED) {
                            Log.i(TAG, "${WorkInfo.State.BLOCKED}")
                        } else if (workInfo.state == WorkInfo.State.ENQUEUED) {
                            Log.i(TAG, "${WorkInfo.State.ENQUEUED}")
                        } else if (workInfo.state == WorkInfo.State.RUNNING) {
                            Log.i(TAG, "${WorkInfo.State.RUNNING}")
                        } else if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                            Log.i(TAG, "${WorkInfo.State.SUCCEEDED}")
                        }
                    }
                })
    }

    companion object {
        const val USER_NAME = "username"
        const val SIMPLE_TAG = "simple_tag"
        val TAG = WorkManagerFragment::class.java.simpleName
    }

}
