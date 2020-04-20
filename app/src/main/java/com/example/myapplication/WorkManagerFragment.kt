package com.example.workmanager


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_work_manager.*

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
            val uploadWorkRequest = OneTimeWorkRequestBuilder<SimpleWorker>()
                .build()
            WorkManager.getInstance(this!!.context!!).enqueue(uploadWorkRequest)
        }
    }

}
