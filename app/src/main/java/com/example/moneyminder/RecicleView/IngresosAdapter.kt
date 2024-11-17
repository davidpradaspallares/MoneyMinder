package com.example.moneyminder.RecicleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyminder.R
import com.example.moneyminder.model_de_datos.Ingresos

class IngresosAdapter (private val listaIngresos:List<Ingresos>): RecyclerView.Adapter<IngresosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngresosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return IngresosViewHolder(layoutInflater.inflate(R.layout.item_ingreso, parent, false))
    }

    override fun onBindViewHolder(holder: IngresosViewHolder, position: Int) {
        val item = listaIngresos[position]
        holder.rener(item)
    }

    override fun getItemCount(): Int = listaIngresos.size

}