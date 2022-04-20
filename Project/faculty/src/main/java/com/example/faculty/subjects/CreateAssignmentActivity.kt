package com.example.faculty.subjects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faculty.R
import kotlinx.android.synthetic.main.activity_create_assignment.*

class CreateAssignmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_assignment)

        val title = intent.getStringExtra("title")
        tv_upload_title.text = title
    }
}