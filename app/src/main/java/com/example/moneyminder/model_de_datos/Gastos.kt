package com.example.moneyminder.model_de_datos

data class Gastos(
    val id: Int?,
    val cantidadGasto: Double,
    val categoria: String,
    val metodoPago: String,
    val descripcion: String,
    val fechaGasto: String
)