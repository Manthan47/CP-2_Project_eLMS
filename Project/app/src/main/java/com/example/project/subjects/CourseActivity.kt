package com.example.project.subjects

import android.content.Intent
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

    private lateinit var mListener: MyAdapterSubject.onItemClickListener

    lateinit var title : ArrayList<String>

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

//        val adapter = MyAdapterSubject(subjectArrayList)
//        subjectRecyclerview.adapter = adapter
//        adapter.setOnItemClickListener(object : MyAdapterSubject.onItemClickListener{
//            override fun onItemClick(position: Int) {
//                Toast.makeText(this@CourseActivity,"Clicked",Toast.LENGTH_SHORT).show()
//            }
//        })

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
        val subada = MyAdapterSubject(subjectArrayList)
        subada.setOnItemClickListener(object : MyAdapterSubject.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@CourseActivity,UploadSubjectMaterialActivity::class.java)
                intent.putExtra("title",subjectArrayList[position].title)
                startActivity(intent)
            }
        })

    }
}