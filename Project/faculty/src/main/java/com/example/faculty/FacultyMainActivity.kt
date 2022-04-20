package com.example.faculty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.faculty.subjects.CourseActivity
import com.example.faculty.subjects.CreateAssignmentActivity
import kotlinx.android.synthetic.main.activity_faculty_main.*

class FacultyMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_main)

        subjectCard.setOnClickListener {
            val intent = Intent(this, CourseActivity::class.java)
            startActivity(intent)
        }

    }
}