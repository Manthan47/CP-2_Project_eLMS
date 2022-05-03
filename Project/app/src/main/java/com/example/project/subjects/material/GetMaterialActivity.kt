package com.example.project.subjects.material

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_get_material.*
import kotlinx.android.synthetic.main.pdfmaterial_list.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class GetMaterialActivity : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerview: RecyclerView
    private lateinit var userArrayList: ArrayList<Material>
    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    var dataUrl:String = ""

    private val REQ_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_material)

        userRecyclerview = findViewById(R.id.materialList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        val title = intent.getStringExtra("title")
        courseTitle.text = title

        userArrayList = arrayListOf<Material>()
        getUserData()

    }

    private fun getUserData() {

        val title = intent.getStringExtra("title")

        dbref = FirebaseDatabase.getInstance().getReference("Subject/$title/UploadFacultyMaterial")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(Material::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerview.adapter = MyAdapterMaterial(userArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val pdfada = MyAdapterMaterial(userArrayList)
        pdfada.setOnItemClickListener(object : MyAdapterMaterial.onItemClickListener{
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onItemClick(position: Int) {

                dataUrl = downloadMaterialUrl.text.toString()

                //dataUrl = "https://firebasestorage.googleapis.com/v0/b/elms-7cde4.appspot.com/o/Subjects%2FFaculty%2FENGLISH%2Fuploads%2Fdaa58fcf-19d6-4d84-96d6-467f87b0a650?alt=media&token=65ef16ef-5c80-483c-b313-fc071a44a933"
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),REQ_CODE)
                } else {
                    startDownloding()
                }

                val toast = Toast.makeText(applicationContext, "Downloading", Toast.LENGTH_SHORT)
                toast.show()
            }
        })


    }

    private fun startDownloding() {
        val request = DownloadManager.Request(Uri.parse(dataUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("Your File is Downloading>>>>>")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"$(System.currentTimeMillis()}")
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

}


