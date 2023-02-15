package com.example.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.reqPermBtn.setOnClickListener {
            intent = Intent(this, RequestPermissionActivity::class.java)
            startActivity(intent)
        }

        mainBinding.reqPermListBtn.setOnClickListener {
            intent = Intent(this, RequestPermissionListenerActivity::class.java)
            startActivity(intent)
        }

        mainBinding.reqPermAsExample.setOnClickListener {
            intent = Intent(this, RequestPermissionAsInExample::class.java)
            startActivity(intent)
        }
    }
}