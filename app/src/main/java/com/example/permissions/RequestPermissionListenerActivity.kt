package com.example.permissions

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
                // launch alert dialog
                // Проверяем, не стоит ли в настройках "запретить"
                // Иначе shouldShow всегда будет false
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
                    // AlertDialog
                    Log.d(TAG, "Camera not permitted after request, before showing alert dialog")
                    showAlertDialog()
                } else {
                    Log.d(TAG,
                        "Should NOT reqPermRation")
                    Toast.makeText(this, "Camera permission is still DENIED", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_permission_listener)

        if (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission already granted")
        } else {
            Log.d(TAG, "Camera not permitted, launching request first time")
            activityResultLauncher.launch(CAMERA)
        }
    }

    private fun showAlertDialog() {
        // show alert dialog
        AlertDialog.Builder(this).setTitle("Alert")
            .setMessage("We really need camera permission.")
            .setPositiveButton("OK") { _, _ ->

                Log.d(TAG, "Alert Dialog OK, before launching request")
                activityResultLauncher.launch(CAMERA)

            }
            .setNegativeButton("NO") { _, _ ->

                Log.d(TAG, "Alert Dialog NO, before launching request")
                Toast.makeText(this, "Camera permission is still DENIED", Toast.LENGTH_SHORT)
                    .show()
            }
            .show()
    }

    companion object {
        const val TAG = "ReqCamPermListenerAct"
    }
}