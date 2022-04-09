package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        student.setOnClickListener {

            var i = Intent(this,AdminStudentList::class.java)
            startActivity(i)
            finish()

        }

        delete_user.setOnClickListener {

            var i = Intent(this,AdminDeleteUser::class.java)
            startActivity(i)
            finish()

        }

    }
}