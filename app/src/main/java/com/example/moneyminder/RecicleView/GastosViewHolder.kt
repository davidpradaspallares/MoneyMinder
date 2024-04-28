package com.example.moneyminder.RecicleView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyminder.R
import com.example.moneyminder.model_de_datos.Gastos

class GastosViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val idGasto = view.findViewById<TextView>(R.id.id)
    val cantidadGasto = view.findViewById<TextView>(R.id.cantidadGasto)
    val categoriaGasto = view.findViewById<TextView>(R.id.categoria)
    val metodoPago = view.findViewById<TextView>(R.id.metodoPago)
    val fechaGasto = view.findViewById<TextView>(R.id.fecha)

    fun rener(gastosModel: Gastos){
        idGasto.text = gastosModel.id.toString()
        fechaGasto.text = gastosModel.fechaGasto.toString()
        cantidadGasto.text = gastosModel.cantidadGasto.toString() + "€"
        categoriaGasto.text = gastosModel.categoria.toString()
        metodoPago.text = gastosModel.metodoPago.toString()
    }
}