package com.example.faculty.subjects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.faculty.R

private lateinit var mListener: MyAdapterSubject.onItemClickListener

class MyAdapterSubject(private val subjectList : ArrayList<Subject>) : RecyclerView.Adapter<MyAdapterSubject.MyViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_courses,
            parent,false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = subjectList[position]
        holder.subjectName.text = currentitem.title
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val subjectName : TextView = itemView.findViewById(R.id.SubjectName)
        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}