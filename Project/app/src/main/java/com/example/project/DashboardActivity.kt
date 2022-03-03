package com.example.project

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import android.R.*
import android.content.Intent

import androidx.drawerlayout.widget.DrawerLayout

import androidx.appcompat.app.AppCompatDelegate

import androidx.coordinatorlayout.widget.CoordinatorLayout

import com.google.android.material.navigation.NavigationView

import android.os.Bundle
import android.telecom.Call
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import android.view.Menu;
import android.widget.Toast

import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.*


class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment

            when (item.itemId) {
                R.id.navigationMyProfile -> return@OnNavigationItemSelectedListener true
                R.id.navigationMyCourses -> return@OnNavigationItemSelectedListener true
                R.id.navigationHome -> return@OnNavigationItemSelectedListener true
                R.id.navigationSearch -> return@OnNavigationItemSelectedListener true
                R.id.navigationMenu -> {
                    val drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
                    drawer.openDrawer(GravityCompat.START)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigationMyProfile -> {
                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationMyCourses -> {
                    Toast.makeText(this, "Courses", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationHome -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationSearch -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationMenu -> {
                    val drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
                    drawer.openDrawer(GravityCompat.START)

                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }


        //Call findViewById on the NavigationView
//        if (DarkModePrefManager(this).isNightMode()) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        }
//        setContentView(R.layout.activity_main)
//        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//        val drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
//        val toggle = ActionBarDrawerToggle(
//            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//        drawer.addDrawerListener(toggle)
//        toggle.syncState()
//        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
//        navigationView.setNavigationItemSelectedListener(this)
//        bottomNavigationView = findViewById(R.id.navigation)
//        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        //
//        val layoutParams = bottomNavigationView.getLayoutParams() as CoordinatorLayout.LayoutParams
//        //layoutParams.behavior = BottomNavigationBehavior()
//        bottomNavigationView.setSelectedItemId(R.id.navigationHome)
    }
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onBackPressed() {
//            val drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id: Int = item.getItemId()
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
            //code for setting dark mode
            //true for dark mode, false for day mode, currently toggling on each click
//            val darkModePrefManager = DarkModePrefManager(this)
//            darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode())
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            recreate()
        }
        val drawer = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}