package com.example.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val bundle = intent.extras
        val picture = bundle!!.getInt("img")
        imageView.setImageResource(picture)
        nameValue.text = intent.getStringExtra("name")
        birthdayValue.text = intent.getStringExtra("birthday")
        cityValue.text = intent.getStringExtra("city")
        descValue.text = intent.getStringExtra("desc")
    }
}
