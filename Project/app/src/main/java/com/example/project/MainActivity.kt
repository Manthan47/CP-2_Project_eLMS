package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to ImageView
        val iv_click_me = findViewById(R.id.goto_register) as ImageView
        // set on-click listener
        iv_click_me.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            val intent = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)

        }
    }

}