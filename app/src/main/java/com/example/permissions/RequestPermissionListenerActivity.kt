package com.example.permissions

import android.Manifest.permission.CAMERA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts

class RequestPermissionListenerActivity : AppCompatActivity() {

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()){ isGranted ->
            // Handle Permission granted/rejected
            if (isGranted) {
                Log.d(TAG, "Camera permission granted")
            } else {
                // Permission is denied
                // FIXME не приходит сюда
                Log.d(TAG, "Camera permission denied")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_permission_listener)

        activityResultLauncher.launch(CAMERA)
    }

    companion object {
        const val TAG = "ReqCamPermListenerAct"
    }
}