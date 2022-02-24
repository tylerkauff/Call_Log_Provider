package com.example.calllog

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Call(
    val number: String,
    val duration: String,
    val type: String,
) {}

class MainActivity : AppCompatActivity() {
    private val permission: String = Manifest.permission.READ_CALL_LOG
    private val requestCode: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            val adapter = PhoneLogAdapter(this, readCallLog())
            recyclerview.adapter = adapter
        }
    }

    private fun readCallLog(): ArrayList<Call> {
        val numberCol = CallLog.Calls.NUMBER
        val durationCol = CallLog.Calls.DURATION
        val typeCol = CallLog.Calls.TYPE

        val projection = arrayOf(numberCol, durationCol, typeCol)
        // Get data from the content provider.
        val cursor = contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            projection, null, null, null
        )

        val numberColIdx = cursor!!.getColumnIndex(numberCol)
        val durationColIdx = cursor.getColumnIndex(durationCol)
        val typeColIdx = cursor.getColumnIndex(typeCol)

        val callList = ArrayList<Call>()

        while (cursor.moveToNext()) {
            val number = cursor.getString(numberColIdx)
            val duration = cursor.getString(durationColIdx)
            val type = cursor.getString(typeColIdx)

            val callObj = Call(number, duration, type)
            callList.add(callObj)

            Log.d("MY_APP", "$number $duration $type")
        }

        cursor.close()
        return callList
    }
}