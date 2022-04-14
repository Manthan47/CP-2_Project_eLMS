package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.subjects.CourseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        val Bottomnavigationview = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        Bottomnavigationview.setOnItemSelectedListener {
            when (it.itemId) {
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

//        val homeFragment = HomeFragment()
//        val settingsFragment = SettingsFragment()
//        val chatFragment = ChatFragment()
//
//        makeCurrentFragment(homeFragment)
//        bottom_navigation.setOnNavigationItemReselectedListener {
//            when (it.itemId){
//                R.id.navigationHome -> makeCurrentFragment(homeFragment)
//                R.id.navigationMyProfile -> makeCurrentFragment(settingsFragment)
//                R.id.navigationChat -> makeCurrentFragment(chatFragment)
//            }
//            true
//        }

    }
//    private fun makeCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply{
//            replace(R.id.fl_wrapper,fragment)
//            commit()
//        }
}