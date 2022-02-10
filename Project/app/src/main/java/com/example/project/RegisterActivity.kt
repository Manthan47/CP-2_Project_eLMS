package com.example.project

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //val setDate = findViewById(R.id.editTextDOB) as EditText

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
//        setDate.setOnClickListener{
//            setDate()
//        }
    }
    private fun setDate() {
        val alarmCalendar = Calendar.getInstance()
        val year: Int = alarmCalendar.get(Calendar.YEAR)
        val month: Int = alarmCalendar.get(Calendar.MONTH)
        val day: Int = alarmCalendar.get(Calendar.DAY_OF_MONTH)
        alarmCalendar.set(year, month, day)
    }

}