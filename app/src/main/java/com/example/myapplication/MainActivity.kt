package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var countNum = 0
        var displayMessage = findViewById<TextView>(R.id.displayMessage)
        displayMessage.text = getString(R.string.count_text, countNum.toString())
        val countButton = findViewById<Button>(R.id.countButton)
        countButton.setOnClickListener {
            countNum++
            displayMessage.text = getString(R.string.count_text, countNum.toString())
        }
    }
}
