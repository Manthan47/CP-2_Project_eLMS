package com.example.faculty.subjects.assignments.checkassignments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faculty.R
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_assignments_list.*

class AssignmentListActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var AssignmentRecyclerview: RecyclerView
    private lateinit var AssignmentArrayList: ArrayList<AssignmentList>
    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignments_list)

        AssignmentRecyclerview = findViewById(R.id.assignmentList)
        AssignmentRecyclerview.layoutManager = LinearLayoutManager(this)
        AssignmentRecyclerview.setHasFixedSize(true)

        val title = intent.getStringExtra("title")
        courseTitle.text = title

        AssignmentArrayList = arrayListOf<AssignmentList>()
        getUserData()

    }
    private fun getUserData() {

        val title = intent.getStringExtra("title")

        dbref = FirebaseDatabase.getInstance().getReference("Subject/$title/Assignments")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(AssignmentList::class.java)
                        AssignmentArrayList.add(user!!)
                    }
                    AssignmentRecyclerview.adapter = MyAdapterAssignmentList(AssignmentArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val assignmnetada = MyAdapterAssignmentList(AssignmentArrayList)
        assignmnetada.setOnItemClickListener(object : MyAdapterAssignmentList.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@AssignmentListActivity,CheckStudentsAssignmentsActivity::class.java)
                intent.putExtra("title1",AssignmentArrayList[position].title)
                intent.putExtra("title",title)
                startActivity(intent)
//                val toast = Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT)
//                toast.show()
            }
        })
    }

}