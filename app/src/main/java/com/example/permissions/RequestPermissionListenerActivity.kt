package com.example.permissions

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class RequestPermissionListenerActivity : AppCompatActivity() {

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()) { isGranted ->
            // Handle Permission granted/rejected
            if (isGranted) {
                Log.d(TAG, "Camera permission granted")
            } else {
                // Permission is denied
                Log.d(TAG, "Camera permission denied")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_permission_listener)

        activityResultLauncher.launch(CAMERA)

        when {
            ContextCompat.checkSelfPermission(
                this,
                CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d(TAG, "Camera permission granted")
            }
            shouldShowRequestPermissionRationale()
            -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                showInContextUI(...)
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.REQUESTED_PERMISSION

                )
            }
        }

    }

    companion object {
        const val TAG = "ReqCamPermListenerAct"
    }
}