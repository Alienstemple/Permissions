package com.example.permissions

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class RequestPermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_permission)

        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Granted
            Log.d(TAG, "Camera permission is already granted")
            Toast.makeText(this, "Camera permission is already granted", Toast.LENGTH_SHORT).show()
        } else {
            // Not granted
            requestCameraPermission()

//            // check shouldLaunch
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
//                // AlertDialog
//                Log.d(RequestPermissionListenerActivity.TAG,
//                    "Camera not permitted after request, before showing alert dialog")
//                showAlertDialog()
//            } else {
//                Log.d(TAG, "Request for the first time")
//                requestCameraPermission()
//            }
        }
    }

    private fun requestCameraPermission() {
        Log.d(TAG, "In requestCameraPerm")
        requestPermissions(arrayOf(CAMERA),
            CAMERA_PERM_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "In onRequestPermissionResult")

        if (requestCode == CAMERA_PERM_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
            ) {
                Log.d(TAG, "Camera permitted")
                updateStatus()

            } else {
                // check shouldLaunch
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
                    // AlertDialog
                    Log.d(RequestPermissionListenerActivity.TAG,
                        "Camera not permitted after request, before showing alert dialog")
                    showAlertDialog()
                } else {
                    Log.d(TAG, "Camera permission is still DENIED")
                    Toast.makeText(this, "Camera permission is still DENIED", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            return
        }
    }

    private fun showAlertDialog() {
        // show alert dialog
        Log.d(TAG, "Camera not permitted, before showing alert dialog")
        //
        AlertDialog.Builder(this).setTitle("Alert")
            .setMessage("We really need camera permission.")
            .setPositiveButton("OK") { _, _ ->
                requestCameraPermission()
            }
            .setNegativeButton("NO") { _, _ ->
                Log.d(TAG, "Camera permission is still DENIED")
                Toast.makeText(this, "Camera permission is still DENIED", Toast.LENGTH_SHORT)
                    .show()
            }
            .show()
    }

    private fun updateStatus() {
        Log.d(TAG, "Camera permission is granted now")
        Toast.makeText(this, "Camera permission is granted now", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "ReqCamPermAct"
        const val CAMERA_PERM_REQUEST_CODE = 101
    }
}