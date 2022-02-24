package com.example.calllog

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val permission: String = Manifest.permission.READ_CALL_LOG
    private val requestCode: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            readCallLog();
        }
    }

    private fun readCallLog() {
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

        while (cursor.moveToNext()) {
            val number = cursor.getString(numberColIdx)
            val duration = cursor.getString(durationColIdx)
            val type = cursor.getString(typeColIdx)

            Log.d("MY_APP", "$number $duration $type")
        }

        cursor.close()
    }
}