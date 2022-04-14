package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.notice.NoticeActivity
import com.example.project.subjects.CourseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        val Bottomnavigationview = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        Bottomnavigationview.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigationNotice->{
                    Intent(this, NoticeActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationHome ->{
                    Intent(this, HomeActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationMyProfile ->{
                    Intent(this, SettingsActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationChat ->{
                    Intent(this, ChatActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationMyCourses ->{
                    Intent(this, CourseActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                else-> {
                    Intent(this, HomeActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true

                }

            }
        }
    }
}