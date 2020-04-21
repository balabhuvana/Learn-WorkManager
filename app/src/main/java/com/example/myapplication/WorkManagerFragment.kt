package com.example.workmanager


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.*
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_work_manager.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class WorkManagerFragment : Fragment() {

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

            val uploadWorkRequest = OneTimeWorkRequestBuilder<SimpleWorker>().setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .build()
            WorkManager.getInstance(this!!.context!!).enqueue(uploadWorkRequest)
        }
    }

}
