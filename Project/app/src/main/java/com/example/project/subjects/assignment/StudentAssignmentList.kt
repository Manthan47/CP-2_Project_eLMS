package com.example.project.subjects.assignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_student_assignment_list.*

class StudentAssignmentList : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerview: RecyclerView
    private lateinit var userArrayList: ArrayList<Assignment>
    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_assignment_list)

        userRecyclerview = findViewById(R.id.assignmentList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        val title = intent.getStringExtra("title")
        courseTitle.text = title

        userArrayList = arrayListOf<Assignment>()
        getUserData()

    }

    private fun getUserData() {

        val title = intent.getStringExtra("title")

        dbref = FirebaseDatabase.getInstance().getReference("Subject/$title/Assignments")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Assignment::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerview.adapter = MyAdapterAssignment(userArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val assignmnetada = MyAdapterAssignment(userArrayList)
        assignmnetada.setOnItemClickListener(object : MyAdapterAssignment.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@StudentAssignmentList,StudentUploadAssignmentActivity::class.java)
                intent.putExtra("title1",userArrayList[position].title)
                intent.putExtra("title",title)
                startActivity(intent)
//                val toast = Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT)
//                toast.show()
            }
        })
    }
}