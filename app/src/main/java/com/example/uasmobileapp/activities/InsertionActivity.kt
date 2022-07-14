package com.example.uasmobileapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uasmobileapp.models.BarangModel
import com.example.uasmobileapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var etKategori: EditText
    private lateinit var etHarga: EditText
    private lateinit var etPrioritas: EditText
    private lateinit var btnSaveData: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)
        etNama = findViewById(R.id.etNama)
        etKategori = findViewById(R.id.etKategori)
        etHarga = findViewById(R.id.etHarga)
        etPrioritas = findViewById(R.id.etPrioritas)
        btnSaveData = findViewById(R.id.btnSaveData)
        dbRef = FirebaseDatabase.getInstance().getReference("Barang")
        btnSaveData.setOnClickListener {
            saveBarangData()
        }
    }

    private fun saveBarangData(){
        val barangNama = etNama.text.toString()
        val barangKategori = etKategori.text.toString()
        val barangHarga = etHarga.text.toString()
        val barangPrioritas = etPrioritas.text.toString()

        if (barangNama.isEmpty()){
            etNama.error = "Silahkan input nama barang"
        }
        if (barangKategori.isEmpty()){
            etKategori.error = "Silahkan input kategori barang"
        }
        if (barangHarga.isEmpty()){
            etHarga.error = "Silahkan input harga barang"
        }
        if (barangPrioritas.isEmpty()){
            etPrioritas.error = "Silahkan input prioritas barang"
        }

        val barangId = dbRef.push().key!!

        val barang = BarangModel(barangId, barangNama, barangKategori, barangHarga, barangPrioritas)

        dbRef.child(barangId).setValue(barang)
            .addOnCompleteListener{
                Toast.makeText(this, "Data berhasil dimasukkan", Toast.LENGTH_LONG).show()

                etNama.text.clear()
                etHarga.text.clear()
                etPrioritas.text.clear()
                etKategori.text.clear()

            }.addOnFailureListener{ err->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}