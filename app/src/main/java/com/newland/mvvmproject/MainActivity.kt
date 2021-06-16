package com.newland.mvvmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newland.mvvmproject.extensions.startActivity
import com.newland.mvvmproject.module.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(RegisterActivity::class.java)
    }
}