package com.example.admin.subject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.AdminStudentList
import com.example.admin.MyAdapter
import com.example.admin.R
import com.example.admin.studentuser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_subject.*
import kotlinx.android.synthetic.main.activity_main.*

class AdminSubjectActivity : AppCompatActivity() {

    private lateinit var  auth: FirebaseAuth
    private lateinit var database : DatabaseReference

    private lateinit var subjectRecyclerview : RecyclerView
    private lateinit var subjectArrayList : ArrayList<Subject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_subject)

        createSubject.setOnClickListener {
            val title=subName.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Subject")
            val subject = Subject(title)
            database.child(title).setValue(subject).addOnSuccessListener {
                subName.setText("")
                Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
        }

        subjectRecyclerview = findViewById(R.id.subjectList)
        subjectRecyclerview.layoutManager = LinearLayoutManager(this)
        subjectRecyclerview.setHasFixedSize(true)

        subjectArrayList = arrayListOf<Subject>()
        getUserData()
    }
    private fun getUserData() {
        database = FirebaseDatabase.getInstance().getReference("Subject")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val subject = userSnapshot.getValue(Subject::class.java)
                        subjectArrayList.add(subject!!)
                    }
                    subjectRecyclerview.adapter = MyAdapterSubject(subjectArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}