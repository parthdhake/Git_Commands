package com.qourall.gitcommands_cheatsheet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.qourall.gitcommands_cheatsheet.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }


    private fun setupViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        navigation.setupWithNavController(navController)
    }
}