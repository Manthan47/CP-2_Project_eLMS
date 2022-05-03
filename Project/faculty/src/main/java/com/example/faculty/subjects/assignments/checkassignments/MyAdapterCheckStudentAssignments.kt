package com.example.faculty.subjects.assignments.checkassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.faculty.R

private lateinit var mListener: MyAdapterCheckStudentAssignments.onItemClickListener

class MyAdapterCheckStudentAssignments(private val CheckStudentAssignmentsList : ArrayList<CheckStudentAssignments>) : RecyclerView.Adapter<MyAdapterCheckStudentAssignments.MyViewHolder>(){
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.student_assignment_list,
            parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = CheckStudentAssignmentsList[position]
        holder.getStudentAssignmentTitle.text = currentitem.title
        holder.downloadStudentAssignmentUrl.text = currentitem.pdfurl
    }

    override fun getItemCount(): Int {
        return CheckStudentAssignmentsList.size
    }

    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val getStudentAssignmentTitle : TextView = itemView.findViewById(R.id.getStudentAssignmentTitle)
        val downloadStudentAssignmentUrl : TextView = itemView.findViewById(R.id.downloadStudentAssignmentUrl)
        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}