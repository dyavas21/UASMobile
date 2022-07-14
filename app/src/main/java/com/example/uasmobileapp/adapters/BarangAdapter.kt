package com.example.uasmobileapp.adapters

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uasmobileapp.R
import com.example.uasmobileapp.activities.BarangDetailActivity
import com.example.uasmobileapp.databinding.BarangListItemBinding
import com.example.uasmobileapp.models.BarangModel

class BarangAdapter (val barangList: ArrayList<BarangModel>,var context: Context?) :
    RecyclerView.Adapter<BarangAdapter.MainViewHolder>(){


    inner class MainViewHolder(val binding: BarangListItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun itemBinding(item : BarangModel) = with(binding){
            binding.tvNamaBarang.text = item.barangName
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(BarangListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with(holder) {
            itemBinding(barangList[position])
            val item = barangList.get(holder.adapterPosition)
            holder.itemView.setOnClickListener {
                val intent = Intent(context, BarangDetailActivity::class.java)
                intent.putExtra("idBarang", item.barangId)
                intent.putExtra("namaBarang", item.barangName)
                intent.putExtra("hargaBarang", item.barangHarga)
                intent.putExtra("kategoriBarang", item.barangKategori)
                intent.putExtra("prioritasBarang", item.barangPrioritas)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context?.startActivity(intent)

            }
        }
    }
    override fun getItemCount(): Int {
        return barangList.size
    }


}