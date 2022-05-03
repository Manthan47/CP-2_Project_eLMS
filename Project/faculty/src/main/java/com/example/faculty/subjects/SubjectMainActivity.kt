package com.example.faculty.subjects

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.faculty.R
import com.example.faculty.subjects.assignments.CreateAssignmentActivity
import com.example.faculty.subjects.assignments.checkassignments.AssignmentListActivity
import kotlinx.android.synthetic.main.activity_subject_main.*

class SubjectMainActivity : AppCompatActivity() {

    private lateinit var subjectArrayList : ArrayList<Subject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_main)

        val title = intent.getStringExtra("title")
        tv_upload_title.text = title

        var title1 = title.toString()

        subjectArrayList = arrayListOf<Subject>()

        facultyUploadMaterial.setOnClickListener {
            val intent = Intent(this,UploadFacultyMaterialActivity::class.java)
            intent.putExtra("title",title1)
            startActivity(intent)
        }

        facultyCreateAssignment.setOnClickListener {
            val intent = Intent(this,CreateAssignmentActivity::class.java)
            intent.putExtra("title",title1)
            startActivity(intent)
        }

        checkStudentAssignment.setOnClickListener {
            val intent = Intent(this,AssignmentListActivity::class.java)
            intent.putExtra("title",title1)
            startActivity(intent)
        }

    }
}

