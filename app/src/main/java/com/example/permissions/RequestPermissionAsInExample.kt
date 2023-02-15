package com.example.permissions

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class RequestPermissionAsInExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_permission_as_in_example)

        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
            Toast.makeText(this, "Разрешение на камеру действительно нужно!", Toast.LENGTH_SHORT)
                .show()
        }

        ActivityCompat.requestPermissions(this, arrayOf(CAMERA), CAMERA_PERM_REQUEST_CODE)
        updateUi()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERM_REQUEST_CODE) {
            updateUi()
        }
    }

    private fun updateUi() {
        val checkPermission =
            ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED
        if (checkPermission)
            Log.d(TAG, "In update ui. Permission granted")
        else
            Log.d(TAG, "In update ui. Permission denied")
    }

    companion object {
        const val TAG = "ReqCamPermExAct"
        const val CAMERA_PERM_REQUEST_CODE = 101
    }
}