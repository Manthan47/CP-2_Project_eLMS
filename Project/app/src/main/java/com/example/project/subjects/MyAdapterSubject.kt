package com.example.project.subjects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R


class MyAdapterSubject(private val subjectList : ArrayList<Subject>) : RecyclerView.Adapter<MyAdapterSubject.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_courses,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = subjectList[position]
        holder.SubjectName.text = currentitem.title
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val SubjectName : TextView = itemView.findViewById(R.id.SubjectName)
    }
}