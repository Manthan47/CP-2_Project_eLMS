package com.example.faculty.subjects

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.faculty.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_subject_main.*
import kotlinx.android.synthetic.main.activity_upload_faculty_material.*

class UploadFacultyMaterialActivity : AppCompatActivity() {

    val pdf: Int=0
    lateinit var uri: Uri
    lateinit var mStorage: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_faculty_material)

        val title = intent.getStringExtra("title")
        Title.text = title

        val chooseFile=findViewById<Button>(R.id.chooseFile)
        mStorage= FirebaseStorage.getInstance().getReference("Subjects/Faculty/$title")

        chooseFile.setOnClickListener(View.OnClickListener {
                view: View-> val intent = Intent()
            intent.type="application/pdf"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select PDF"),pdf)
        })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uriTxt=findViewById<TextView>(R.id.uriTxt)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==pdf){
                uri= data!!.data!!
                uriTxt.text=uri.toString()
                uploadFacultyMaterial.setOnClickListener {
                    upload()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload() {
        var mReference = mStorage.child(uri.lastPathSegment.toString())
        try {
            mReference.putFile(uri).addOnSuccessListener {
                    taskSnapshot: UploadTask.TaskSnapshot? -> var url = taskSnapshot!!.getStorage().getDownloadUrl()
                val dwnTxt = findViewById<View>(R.id.dwnTxt) as TextView
                dwnTxt.text = url.toString()
                Toast.makeText(this, "Successfully Uploaded :)", Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }

    }
}