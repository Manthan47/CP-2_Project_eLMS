package com.example.faculty.subjects.assignments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.faculty.R
import com.example.faculty.subjects.FacultyUploadMaterial
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_create_assignment.*
import kotlinx.android.synthetic.main.activity_create_assignment.Title
import kotlinx.android.synthetic.main.activity_upload_faculty_material.*
import java.util.*

class CreateAssignmentActivity : AppCompatActivity() {
    val pdf: Int=0
    lateinit var uri: Uri
    lateinit var mStorage: StorageReference
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_assignment)

        val title = intent.getStringExtra("title")
        Title.text = title

        val chooseFile=findViewById<Button>(R.id.chooseFile)
        mStorage= FirebaseStorage.getInstance().getReference("Subjects/Faculty/$title/Assignments")

        chooseAssignmentFile.setOnClickListener(View.OnClickListener {
                view: View -> val intent = Intent()
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
                createAssignment.setOnClickListener {
                    upload()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

//    private fun upload() {
//        var mReference = mStorage.child(uri.lastPathSegment.toString())
//        try {
//            mReference.putFile(uri).addOnSuccessListener {
//                    taskSnapshot: UploadTask.TaskSnapshot? -> var url = taskSnapshot!!.storage.downloadUrl
//                val dwnTxt = findViewById<View>(R.id.dwnTxt) as TextView
//                dwnTxt.text = url.toString()
//                Toast.makeText(this, "Successfully Uploaded :)", Toast.LENGTH_LONG).show()
//
//                    val title = intent.getStringExtra("title")
//                    Title.text = title
//
//                    val materialtitle=materialTitle.text.toString()
//                    database = FirebaseDatabase.getInstance().getReference("Subject/$title/UploadFacultyMaterial")
//
//                    var pdfUrl = url.toString()
//
//                    val upfacmaterial = FacultyUploadMaterial(materialtitle, pdfUrl)
//
//                    database.child(materialtitle).setValue(upfacmaterial).addOnSuccessListener {
//
//                        Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
//
//                    }.addOnFailureListener{
//
//                        Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
//
//                    }
//
//            }
//        }catch (e: Exception) {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
//        }
//
//    }

    private fun upload(){
        if(uri != null){
            val ref = mStorage?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(uri!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    var pdfUrl = downloadUri.toString()

                    val title = intent.getStringExtra("title")
                    Title.text = title

                    val assignmenttitle=assignmentTitle.text.toString()

                    database = FirebaseDatabase.getInstance().getReference("Subject/$title/Assignments")

                    val upfacmaterial = FacultyUploadMaterial(assignmenttitle, pdfUrl)
                    database.child(assignmenttitle).setValue(upfacmaterial).addOnSuccessListener {

                        Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener{

                        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()

                    }

                    addUploadRecordToDb(downloadUri.toString())
                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(this, "Please Upload an PDF", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUploadRecordToDb(uri: String){
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["pdfUrl"] = uri

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Saved to DB", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving to DB", Toast.LENGTH_LONG).show()
            }
    }
}