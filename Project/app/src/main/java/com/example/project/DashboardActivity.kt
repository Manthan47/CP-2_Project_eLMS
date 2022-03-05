package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project.fragments.ChatFragment
import com.example.project.fragments.HomeFragment
import com.example.project.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val homeFragment = HomeFragment()
        val settingsFragment = SettingsFragment()
        val chatFragment = ChatFragment()

        makeCurrentFragment(homeFragment)
        bottom_navigation.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.navigationHome -> makeCurrentFragment(homeFragment)
                R.id.navigationMyProfile -> makeCurrentFragment(settingsFragment)
                R.id.navigationChat -> makeCurrentFragment(chatFragment)
            }
            true
        }

    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}