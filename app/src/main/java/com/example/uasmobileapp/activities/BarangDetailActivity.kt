package com.example.uasmobileapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.uasmobileapp.R
import org.w3c.dom.Text

class BarangDetailActivity : AppCompatActivity() {

    private lateinit var tvIDDetail: TextView
    private lateinit var tvNamaDetail: TextView
    private lateinit var tvKategoriDetail: TextView
    private lateinit var tvHargaDetail: TextView
    private lateinit var tvPrioritasDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang_detail)
        tvIDDetail = findViewById(R.id.tvIDDetail)
        tvNamaDetail = findViewById(R.id.tvNamaDetail)
        tvKategoriDetail = findViewById(R.id.tvKategoriDetail)
        tvHargaDetail = findViewById(R.id.tvHargaDetail)
        tvPrioritasDetail = findViewById(R.id.tvPrioritasDetail)

        val id= intent.getStringExtra("BarangID")
        val nama= intent.getStringExtra("namaBarang")
        val kategori = intent.getStringExtra("kategoriBarang")
        val harga= intent.getStringExtra("hargaBarang")
        val prioritas = intent.getStringExtra("prioritasBarang")

        Toast.makeText(applicationContext, "listnya : ${id},${nama},${kategori},${harga},${prioritas},", Toast.LENGTH_SHORT).show()

        tvIDDetail.setText(id)
        tvNamaDetail.setText(nama)
        tvKategoriDetail.setText(kategori)
        tvHargaDetail.setText(harga)
        tvPrioritasDetail.setText(prioritas)
    }

}