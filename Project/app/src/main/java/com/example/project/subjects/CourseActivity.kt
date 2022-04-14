package com.example.project.subjects

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.google.firebase.database.*

class CourseActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    private lateinit var subjectRecyclerview : RecyclerView
    private lateinit var subjectArrayList : ArrayList<Subject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

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