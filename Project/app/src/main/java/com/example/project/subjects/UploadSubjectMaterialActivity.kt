package com.example.project.subjects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import kotlinx.android.synthetic.main.activity_upload_subject_material.*

class UploadSubjectMaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_subject_material)

        val title = intent.getStringExtra("title")
        tv_upload_title.text = title
    }
}