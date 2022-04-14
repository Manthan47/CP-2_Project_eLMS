package com.example.project.notice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.nfc.tech.IsoDep.get
import android.provider.ContactsContract.ProfileSyncState.get
import androidx.lifecycle.ViewTreeLifecycleOwner.get
import com.squareup.picasso.Picasso
import org.chromium.base.Promise
import java.lang.reflect.Array.get
import java.net.URL

class MyAdapterNotice(private val userList : ArrayList<Notice>) : RecyclerView.Adapter<MyAdapterNotice.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.notice_list,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        val url = currentitem.imageurl

        Picasso.get().load(url).into(holder.noticeImg)
        holder.noticeTitle.text = currentitem.title
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val noticeTitle : TextView = itemView.findViewById(R.id.getnoticeTitle)
        val noticeImg : ImageView = itemView.findViewById(R.id.getnoticeimg)
    }

}