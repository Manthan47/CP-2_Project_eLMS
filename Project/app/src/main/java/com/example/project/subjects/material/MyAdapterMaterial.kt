package com.example.project.subjects.material

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import kotlinx.android.synthetic.main.pdfmaterial_list.*

private lateinit var mListener: MyAdapterMaterial.onItemClickListener

class MyAdapterMaterial(private val userList : ArrayList<Material>) : RecyclerView.Adapter<MyAdapterMaterial.MyViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.pdfmaterial_list,
            parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.getMaterialTitle.text = currentitem.title
        holder.downloadMaterialUrl.text = currentitem.pdfurl
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val getMaterialTitle : TextView = itemView.findViewById(R.id.getMaterialTitle)
        val downloadMaterialUrl : TextView = itemView.findViewById(R.id.downloadMaterialUrl)
        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}