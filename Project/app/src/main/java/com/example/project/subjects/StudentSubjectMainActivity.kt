package com.example.project.subjects

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.subjects.assignment.StudentAssignmentList
import com.example.project.subjects.material.GetMaterialActivity
import kotlinx.android.synthetic.main.activity_student_subject_main_activity.*

class StudentSubjectMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_subject_main_activity)

        val title = intent.getStringExtra("title")
        sub_title.text = title

        var title1 = title.toString()

        downloadFacMaterials.setOnClickListener {
            val intent  = Intent(this,GetMaterialActivity::class.java)
            intent.putExtra("title",title1)
            startActivity(intent)
        }

        uploadAssignment.setOnClickListener {
            val intent  = Intent(this,StudentAssignmentList::class.java)
            intent.putExtra("title",title1)
            startActivity(intent)
        }
    }
}