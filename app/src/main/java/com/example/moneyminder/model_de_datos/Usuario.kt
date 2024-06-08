package com.example.moneyminder.model_de_datos

data class Usuario(
    val nombre: String,
    val apellidos: String,
    val correoElectronico: String,
    val telefono: Int,
    val salarioMensual: Double,
    val diaIngresoSalario: Int,
    val copiaSeguridad: Boolean,
    val contraseña: String? = ""
)