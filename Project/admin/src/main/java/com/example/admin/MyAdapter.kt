package com.example.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList : ArrayList<studentuser>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.students_list,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.rollno.text = currentitem.rollno
        holder.name.text = currentitem.name
        holder.email.text = currentitem.email
        holder.mobileno.text = currentitem.mobileno
        holder.dob.text = currentitem.dob

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val rollno : TextView = itemView.findViewById(R.id.tvrollno)
        val name : TextView = itemView.findViewById(R.id.tvName)
        val email : TextView = itemView.findViewById(R.id.tvEmail)
        val mobileno : TextView = itemView.findViewById(R.id.tvmobileno)
        val dob : TextView = itemView.findViewById(R.id.tvdob)

    }

}