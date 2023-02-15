package com.example.permissions

import android.Manifest
import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class RequestPermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_permission)

        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Granted
            // TODO snackbar
            Toast.makeText(this, "Camera permission is already granted", Toast.LENGTH_SHORT).show()
        } else {
            // Not granted
            requestCameraPermission()
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
                requestPermissionWithRationale()
            }
            return
        }
    }

    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                CAMERA)
        ) {
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
                    Toast.makeText(this, "Camera permission is still DENIED", Toast.LENGTH_SHORT).show()
                }
                .show()
        } else {
            requestCameraPermission()
        }
    }

    private fun updateStatus() {
        if (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission is granted now")
            Toast.makeText(this, "Camera permission is granted now", Toast.LENGTH_SHORT).show()
        } else {
            Log.d(TAG, "Camera permission is DENIED now")
            Toast.makeText(this, "Camera permission is DENIED now", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val TAG = "ReqCamPermAct"
        const val CAMERA_PERM_REQUEST_CODE = 101
    }
}