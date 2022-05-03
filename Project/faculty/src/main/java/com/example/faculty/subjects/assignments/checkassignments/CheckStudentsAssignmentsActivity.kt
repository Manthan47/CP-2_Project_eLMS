package com.example.faculty.subjects.assignments.checkassignments

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faculty.R
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.student_assignment_list.*

class CheckStudentsAssignmentsActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var StudentAssignmentRecyclerview: RecyclerView
    private lateinit var StudentAssignmentArrayList: ArrayList<CheckStudentAssignments>
    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    var dataUrl:String = ""
    private val REQ_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_students_assignments)

        StudentAssignmentRecyclerview = findViewById(R.id.StudentAssignmentList)
        StudentAssignmentRecyclerview.layoutManager = LinearLayoutManager(this)
        StudentAssignmentRecyclerview.setHasFixedSize(true)

        val title = intent.getStringExtra("title")
        val title1 = intent.getStringExtra("title1")
        //courseTitle.text = title

        StudentAssignmentArrayList = arrayListOf<CheckStudentAssignments>()
        getUserData()

    }
    private fun getUserData() {

        val title = intent.getStringExtra("title")
        val title1 = intent.getStringExtra("title1")

        dbref = FirebaseDatabase.getInstance().getReference("Subject/$title/StudentAssignments/$title1")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(CheckStudentAssignments::class.java)
                        StudentAssignmentArrayList.add(user!!)
                    }
                    StudentAssignmentRecyclerview.adapter = MyAdapterCheckStudentAssignments(StudentAssignmentArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val assignmnetada = MyAdapterCheckStudentAssignments(StudentAssignmentArrayList)
        assignmnetada.setOnItemClickListener(object : MyAdapterCheckStudentAssignments.onItemClickListener{
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onItemClick(position: Int) {
//                val toast = Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT)
//                toast.show()

                dataUrl = downloadStudentAssignmentUrl.text.toString()

                //dataUrl = "https://firebasestorage.googleapis.com/v0/b/elms-7cde4.appspot.com/o/Subjects%2FFaculty%2FENGLISH%2Fuploads%2Fdaa58fcf-19d6-4d84-96d6-467f87b0a650?alt=media&token=65ef16ef-5c80-483c-b313-fc071a44a933"
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),REQ_CODE)
                } else {
                    startDownloding()
                }

                val toast = Toast.makeText(applicationContext, "Downloading", Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }

    private fun startDownloding() {
        val request = DownloadManager.Request(Uri.parse(dataUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("Your File is Downloading>>>>>")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"$(System.currentTimeMillis()}")
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

}