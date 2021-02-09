package com.qourall.gitcommands_cheatsheet.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.qourall.gitcommands_cheatsheet.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed(
                {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                },
                500
        )
    }
}