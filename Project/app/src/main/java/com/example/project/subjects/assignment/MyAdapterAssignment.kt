package com.example.project.subjects.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R

private lateinit var mListener: MyAdapterAssignment.onItemClickListener

class MyAdapterAssignment(private val assignmentList: ArrayList<Assignment>) : RecyclerView.Adapter<MyAdapterAssignment.MyViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.assignment_list,
            parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = assignmentList[position]
        holder.assignmentTitle.text = currentitem.title
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val assignmentTitle : TextView = itemView.findViewById(R.id.assignmentTitle)
        //val downloadMaterial : TextView = itemView.findViewById(R.id.downloadMaterial)
        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}