package com.example.uasmobileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ViewAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uasmobileapp.R
import com.example.uasmobileapp.adapters.BarangAdapter
import com.example.uasmobileapp.models.BarangModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class FetchingActivity : AppCompatActivity() {

    private lateinit var barangRecyclerView : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var barangList : ArrayList<BarangModel>
    private lateinit var dbRef : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)
        barangRecyclerView = findViewById(R.id.rvBarang)
        barangRecyclerView.layoutManager = LinearLayoutManager(this)
        barangRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        barangList = arrayListOf<BarangModel>()
        getBarangData()
    }

    private fun getBarangData(){
        barangRecyclerView.visibility = View.GONE
        tvLoadingData.visibility  = View.VISIBLE

        val dbRef = FirebaseDatabase.getInstance().getReference("Barang")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                barangList.clear()
                if (snapshot.exists()){
                    for (barangSnap in snapshot.children){
                        val barangData = barangSnap.getValue(BarangModel::class.java)
                        barangList.add(barangData!!)
                    }
                    val mAdapter = BarangAdapter(barangList,applicationContext)
                    barangRecyclerView.adapter = mAdapter

                    barangRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}