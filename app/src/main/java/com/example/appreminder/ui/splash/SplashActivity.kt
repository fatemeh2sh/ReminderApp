package com.example.appreminder.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.appreminder.databinding.ActivitySplashBinding
import com.example.appreminder.ui.main.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRunnable = Runnable {
            Intent(this,MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        mHandler = Handler(mainLooper)
        mHandler.postDelayed(mRunnable, 4000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }
}