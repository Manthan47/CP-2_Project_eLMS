package com.example.admin

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_admin_deleteuser.*

class AdminDeleteUser : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_deleteuser)

        val cirLoginButton = findViewById<TextView>(R.id.cirDelete)
        cirLoginButton.setOnClickListener {
            var username = get_username_text.text.toString()
            if(username.isNotEmpty())
                deleteData(username)
            else
                Toast.makeText(this,"Please enter valid user name",Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(username : String){

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(username).removeValue().addOnSuccessListener{
            get_username_text.text.clear()
            Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed To Delete",Toast.LENGTH_SHORT).show()
        }
    }
}