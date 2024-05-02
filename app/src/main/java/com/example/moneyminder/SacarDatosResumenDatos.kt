package com.example.moneyminder

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb
import com.example.moneyminder.model_de_datos.Gastos
import com.example.moneyminder.model_de_datos.Usuario
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SacarDatosResumenDatos(val context: Context) {

    var crearDb = CrearDb(context)
    val db = crearDb.writableDatabase

    //Extrae y suma el total de todos los gastos historicos sin ningún filtro.
    fun sumarTodosLosGastos(): Double{
        var listaGastos: List<Gastos> = LeerDatosDb().leerGastos(db)
        //Sumamos das las cantiades de cantidadGasto.
        var totalGasto = listaGastos.sumOf { it.cantidadGasto }
        return totalGasto
    }

    fun sumarGastosMesActual(): Double{
        var listaGastos: List<Gastos> = LeerDatosDb().leerGastos(db)
        var listaGastosFiltrado: List<Gastos>
        var contador = 0
        var gastoTotal = 0.00
        for(item in listaGastos){
            if(listaGastos[contador].fechaGasto.toString().substring(3) == obtenerMesYAnioActual().toString() ){
                gastoTotal += listaGastos[contador].cantidadGasto
            }
            contador++
        }
        return gastoTotal

    }

    // Tu mes: 30 dias teniendo en cuenta el día en el que cobras.
    fun sumarGastosTuMes(): Double{
        var listaGastos: List<Gastos> = LeerDatosDb().leerGastos(db)
        var gastoTotal = 0.00
        var contador = 0
        //Comprobamos si este mes hemos sobrepasado el dia de ingreso
        if (LocalDate.now().dayOfMonth < LeerDatosDb().getDiaIngresoSalario(db)){ //Si es anterior al 10
            for(item in listaGastos){//Sacamos los datos del mes en curso.
                if(listaGastos[contador].fechaGasto.toString().substring(3) == obtenerMesYAnioActual().toString() &&
                    listaGastos[contador].fechaGasto.toString().substring(0,2).toInt() <= 9  ){
                    gastoTotal += listaGastos[contador].cantidadGasto
                }
                contador++
            }
            contador = 0
            var fecha = "0" + (obtenerMesYAnioActual().substring(0,2).toInt()-1) + "-" + obtenerMesYAnioActual().substring(3)
            for(item in listaGastos){//Sacamos los datos del mes anterior.
                if(listaGastos[contador].fechaGasto.toString().substring(3) == fecha &&
                    listaGastos[contador].fechaGasto.toString().substring(0,2).toInt() >= 10  ){
                    gastoTotal += listaGastos[contador].cantidadGasto
                }
                contador++
            }
        }else if(LocalDate.now().dayOfMonth > LeerDatosDb().getDiaIngresoSalario(db)){
            contador = 0
            for(item in listaGastos){//Sacamos los datos del mes en curso.
                if(listaGastos[contador].fechaGasto.toString().substring(3) == obtenerMesYAnioActual().toString() &&
                    listaGastos[contador].fechaGasto.toString().substring(0,2).toInt() >= 10  ){
                    gastoTotal += listaGastos[contador].cantidadGasto
                }
                contador++
            }
            var fecha = "0" + (obtenerMesYAnioActual().substring(0,2).toInt()+1) + "-" + obtenerMesYAnioActual().substring(3)
            for(item in listaGastos){//Sacamos los datos del mes posterior.
                if(listaGastos[contador].fechaGasto.toString().substring(3) == fecha &&
                    listaGastos[contador].fechaGasto.toString().substring(0,2).toInt() <= 9  ){
                    gastoTotal += listaGastos[contador].cantidadGasto
                }
                contador++
            }

        }
        return  gastoTotal
    }

    fun obtenerMesYAnioActual(): String {
        val calendar = Calendar.getInstance()
        val mes = calendar.get(Calendar.MONTH) + 1 // Los meses en Calendar van de 0 a 11
        val anio = calendar.get(Calendar.YEAR)

        val formatoFecha = SimpleDateFormat("MM-yyyy", Locale.getDefault()) // Personaliza el formato aquí
        return formatoFecha.format(Date(calendar.timeInMillis))
    }

    //Restamos del salario mensual los gastos que hayamos tenido ese mes.
    fun DineroRestanteMes(): Double{
        //Extraemos los datos del usuario y seleccionando el salario mensual le restamos la suma de todos los gastos de este mes.
        try {
            var listaDatosUsuario: List<Usuario> = LeerDatosDb().leerDatosUsuario(db)
            return listaDatosUsuario[0].salarioMensual - sumarGastosMesActual()
        }catch (e: Exception){
            return 0.00
        }
    }

    fun catMasRecurrente(): List<String> {
        try {
            return listOf(
                LeerDatosDb().leerCategoriasGastos(db)[0] + " - " + LeerDatosDb().leerCantidadVecesCategorias(db)[0],
                LeerDatosDb().leerCategoriasGastos(db)[1] + " - " + LeerDatosDb().leerCantidadVecesCategorias(db)[1],
                LeerDatosDb().leerCategoriasGastos(db)[2] + " - " + LeerDatosDb().leerCantidadVecesCategorias(db)[2]
            )
        }catch (e: Exception){
            return listOf("-","-","-")
        }

    }

    fun catMasGasto():List<String>{
        try {
            return listOf(
                LeerDatosDb().leerCategoriaMayorGasto(db)[0] + " - " + LeerDatosDb().leerSumaEconomicaCategorias(db)[0] + "€",
                LeerDatosDb().leerCategoriaMayorGasto(db)[1] + " - " + LeerDatosDb().leerSumaEconomicaCategorias(db)[1] + "€",
                LeerDatosDb().leerCategoriaMayorGasto(db)[2] + " - " + LeerDatosDb().leerSumaEconomicaCategorias(db)[2] + "€"
            )
        }catch (e: Exception){
            return listOf("-","-","-")
        }
    }

}