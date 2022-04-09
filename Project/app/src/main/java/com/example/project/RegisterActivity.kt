package com.example.project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        //val setDate = findViewById(R.id.editTextDOB) as EditText

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
//        setDate.setOnClickListener{
//            setDate()
//        }

        // get reference to ImageView
        val iv_click_me = findViewById(R.id.goto_login) as ImageView
        // set on-click listener
        iv_click_me.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextDOB = findViewById<EditText>(R.id.editTextDOB)
        val editTextRollN = findViewById<EditText>(R.id.editTextRollN)
        val editTextMobile = findViewById<EditText>(R.id.editTextMobile)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)

        auth=FirebaseAuth.getInstance()

        val cirRegisterButton = findViewById<TextView>(R.id.cirRegisterButton)
        cirRegisterButton.setOnClickListener {

            val name=editTextName.text.toString()
            val email=editTextEmail.text.toString()
            val dob=editTextDOB.text.toString()
            val rollno=editTextRollN.text.toString()
            val mobileno=editTextMobile.text.toString()
            val password=editTextPassword.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name,email,dob,rollno,mobileno,password)
            database.child(name).setValue(User).addOnSuccessListener {

//                binding.name.text.clear()
//                binding.email.text.clear()
//                binding.dob.text.clear()
//                binding.rollno.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }

            register()
            goToLogin()
        }
    }

    fun register(){

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextDOB = findViewById<EditText>(R.id.editTextDOB)
        val editTextRollN = findViewById<EditText>(R.id.editTextRollN)
        val editTextMobile = findViewById<EditText>(R.id.editTextMobile)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)

        val name=editTextName.text.toString()
        val email=editTextEmail.text.toString()
        val dob=editTextDOB.text.toString()
        val rollno=editTextRollN.text.toString()
        val mobileno=editTextMobile.text.toString()
        val password=editTextPassword.text.toString()
        val confirmpassword=editTextConfirmPassword.text.toString()

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)

                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    fun goToLogin(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun setDate() {
        val alarmCalendar = Calendar.getInstance()
        val year: Int = alarmCalendar.get(Calendar.YEAR)
        val month: Int = alarmCalendar.get(Calendar.MONTH)
        val day: Int = alarmCalendar.get(Calendar.DAY_OF_MONTH)
        alarmCalendar.set(year, month, day)
    }

}