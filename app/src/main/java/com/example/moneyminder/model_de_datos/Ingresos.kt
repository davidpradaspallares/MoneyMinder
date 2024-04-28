package com.example.moneyminder.model_de_datos

data class Ingresos(
    val id: Int,
    val cantidadIngreso: Double,
    val procedenciaIngreso: String,
    val metodoIngreso: String,
    val descripcion: String,
    val fechaIngreso: String
)