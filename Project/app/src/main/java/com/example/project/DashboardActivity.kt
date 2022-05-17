package com.example.project

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.subjects.CourseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.content_main.*


class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var stuName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        seeAll.setOnClickListener {
            val i = Intent(this, CourseActivity::class.java)
            startActivity(i)
        }

        val Bottomnavigationview = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        Bottomnavigationview.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigationHome -> {
                    Intent(this, HomeActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationMyProfile -> {
                    Intent(this, SettingsActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationChat -> {
                    Intent(this, ChatActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.navigationMyCourses -> {
                    Intent(this, CourseActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                else -> {
                    Intent(this, HomeActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true

                }

            }

        }

//        auth = FirebaseAuth.getInstance()
//        stuName = auth.currentUser?.uid.toString()
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//        if (stuName.isNotEmpty()) {
//            getuserData()
//        }
    }
//    private fun getuserData(){
//        databaseReference.child(stuName).addValueEventListener(object: ValueEventListener {
//            override fun onDataChange (snapshot: DataSnapshot){
//                user = snapshot.getValue(User::class.java)!!
//                studentName.text = user.name
//            }
//            override fun onCancelled(error: DatabaseError){
//                    TODO("Not yet implemented")
//            }
//        })
//    }
}