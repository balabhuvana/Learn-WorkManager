package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workmanager.WorkManagerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setWorkManagerFragment()
    }

    private fun setWorkManagerFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.myWorkManagerFrameLayout, WorkManagerFragment()).commit()
    }
}
