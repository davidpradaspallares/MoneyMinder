package com.example.moneyminder

import android.content.Context
import android.content.Intent

class PasarVentanas {

    constructor(contexto: Context, destino: String){
        var intent = Intent();
        when(destino){
            "ResumenGastos" -> {intent = Intent(contexto, ResumenGastos::class.java)}
            "PanelControlAlarma" -> {intent = Intent(contexto, PanelControlAlarma::class.java)}
            "VerDatosUsuario" -> {intent = Intent(contexto, VerDatosPersonales::class.java)}
            "FormularioNuevoGasto" -> {intent = Intent(contexto, FormularioNuevoGasto::class.java)}
        }

        contexto.startActivity(intent)
    }

}