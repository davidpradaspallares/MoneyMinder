package com.example.moneyminder.RecicleView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyminder.R
import com.example.moneyminder.model_de_datos.Gastos
import com.example.moneyminder.model_de_datos.Ingresos

class IngresosViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val idIngreso = view.findViewById<TextView>(R.id.id)
    val cantidadIngreso = view.findViewById<TextView>(R.id.cantidadGasto)
    val categoriaIngreso = view.findViewById<TextView>(R.id.categoria)
    val metodoIngreso = view.findViewById<TextView>(R.id.metodoPago)
    val fechaIngreso = view.findViewById<TextView>(R.id.fecha)

    fun rener(ingresoModel: Ingresos){
        idIngreso.text = ingresoModel.id.toString()
        fechaIngreso.text = ingresoModel.fechaIngreso.toString()
        cantidadIngreso.text = String.format("%.2f",ingresoModel.cantidadIngreso).toString() + "€"
        metodoIngreso.text = ingresoModel.metodoIngreso.toString()
    }
}