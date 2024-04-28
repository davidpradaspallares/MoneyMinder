package com.example.moneyminder.db

import android.database.sqlite.SQLiteDatabase
import com.example.moneyminder.model_de_datos.Gastos
import com.example.moneyminder.model_de_datos.Ingresos

class LeerDatosDb {

    //Leemos todos los gastos de de la tabla "gastos", la almacenamos en una lista de tipo Gasto y retornamos la lista.
    fun leerGastos(db: SQLiteDatabase): MutableList<Gastos> {
        val cursor = db.rawQuery("SELECT * FROM gastos", null)
        val listaDatosGasto = mutableListOf<Gastos>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val cantidadGasto = cursor.getDouble(1)
            val categoriaPrincipal = cursor.getString(2)
            val metodoPago = cursor.getString(3)
            val descripcion = cursor.getString(4)
            val fecha = cursor.getString(5)

            listaDatosGasto.add(Gastos(id, cantidadGasto,categoriaPrincipal,metodoPago,descripcion, fecha))
        }
        cursor.close()

        return listaDatosGasto
    }

    //Leemos todos los ingresos de de la tabla "ingresos", la almacenamos en una lista de tipo ingreso y retornamos la lista.
    fun leerIngresos(db: SQLiteDatabase): MutableList<Ingresos> {
        val cursor = db.rawQuery("SELECT * FROM ingresos", null)
        val listaDatosIngreso = mutableListOf<Ingresos>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val cantidadIngreso = cursor.getDouble(1)
            val procedenciaIngreso = cursor.getString(2)
            val metodoIngreso = cursor.getString(3)
            val descripcion = cursor.getString(4)
            val fechaIngreso = cursor.getString(5)

            listaDatosIngreso.add(Ingresos(id, cantidadIngreso,procedenciaIngreso,metodoIngreso,descripcion,fechaIngreso))
        }
        cursor.close()
        return listaDatosIngreso
    }


}