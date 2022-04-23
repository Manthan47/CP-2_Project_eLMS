package com.example.project.subjects.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_get_material.*
import kotlinx.android.synthetic.main.pdfmaterial_list.*
import java.io.File

class GetMaterialActivity : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerview: RecyclerView
    private lateinit var userArrayList: ArrayList<Material>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_material)

        userRecyclerview = findViewById(R.id.materialList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        val title = intent.getStringExtra("title")
        courseTitle.text = title

        userArrayList = arrayListOf<Material>()
        getUserData()

    }

    private fun getUserData() {

        val title = intent.getStringExtra("title")

        dbref = FirebaseDatabase.getInstance().getReference("Subject/$title/UploadFacultyMaterial")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Material::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerview.adapter = MyAdapterMaterial(userArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}