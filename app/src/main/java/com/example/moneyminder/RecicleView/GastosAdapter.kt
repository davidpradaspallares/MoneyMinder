package com.example.moneyminder.RecicleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyminder.R
import com.example.moneyminder.model_de_datos.Gastos

class GastosAdapter (private val listaGastos:List<Gastos>): RecyclerView.Adapter<GastosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GastosViewHolder(layoutInflater.inflate(R.layout.item_gasto, parent, false))
    }

    override fun getItemCount(): Int = listaGastos.size

    override fun onBindViewHolder(holder: GastosViewHolder, position: Int) {
        val item = listaGastos[position]
        holder.rener(item)
    }
}