package com.example.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Picasso.get().load(intent.getStringExtra("img")).into(imageView)
        nameValue.text = intent.getStringExtra("name")
        birthdayValue.text = intent.getStringExtra("birthday")
        cityValue.text = intent.getStringExtra("city")
        descValue.text = intent.getStringExtra("desc")
    }
}
