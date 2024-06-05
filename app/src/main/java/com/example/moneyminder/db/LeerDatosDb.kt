package com.example.moneyminder.db

import android.database.sqlite.SQLiteDatabase
import com.example.moneyminder.model_de_datos.Gastos
import com.example.moneyminder.model_de_datos.Ingresos
import com.example.moneyminder.model_de_datos.Usuario

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
    fun leerDatosUsuario(db: SQLiteDatabase): MutableList<Usuario>{
        val cursor = db.rawQuery("SELECT * FROM datos_personales", null)
        val listaDatosUsuario = mutableListOf<Usuario>()

        while (cursor.moveToNext()) {
            val nombre = cursor.getString(0)
            val apellidos = cursor.getString(1)
            val correoElectronico = cursor.getString(2)
            val telefono = cursor.getInt(3)
            val salarioMensual = cursor.getDouble(4)
            val diaIngresoSalario = cursor.getInt(5)

            listaDatosUsuario.add(Usuario(nombre, apellidos,correoElectronico,telefono,salarioMensual,diaIngresoSalario))
        }
        cursor.close()
        return listaDatosUsuario
    }
    fun leerCantidadVecesCategoriasAñoActualNombre(db: SQLiteDatabase): MutableList<String> {
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal\n" +
                "FROM gastos\n" +
                "WHERE SUBSTR(fecha_gasto, 7) = strftime('%Y', 'now') -- Comparar solo el año\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY COUNT(*) DESC", null)
        val categorias = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val categoria = cursor.getString(0)
            categorias.add(categoria)

        }
        cursor.close()

        return categorias
    }

    //Lee las veces que se repite una categoría
    fun leerVecesCategoriasGastos(db: SQLiteDatabase): MutableList<String> {
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, COUNT(*) AS conteo_repeticiones\n" +
                "FROM gastos\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY conteo_repeticiones DESC;", null)
        val categorias = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val categoria = cursor.getString(0)
            categorias.add(categoria)

        }
        cursor.close()

        return categorias
    }

    fun leerCantidadVecesCategoriasAñoActual(db: SQLiteDatabase): MutableList<Int>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, COUNT(*) AS conteo_repeticiones\n" +
                "FROM gastos\n" +
                "WHERE SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY conteo_repeticiones DESC;\n", null)
        val conteo = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            conteo.add(cursor.getInt(1))
        }
        cursor.close()
        return conteo
    }
    fun leerCantidadVecesCategoriasMesActual(db: SQLiteDatabase): MutableList<Int>{
        val cursor = db.rawQuery("SELECT \n" +
                "    categoria_principal, \n" +
                "    COUNT(*) AS conteo_repeticiones,\n" +
                "    SUM(cantidad_gasto) AS total_gasto\n" +
                "FROM \n" +
                "    gastos\n" +
                "WHERE \n" +
                "    SUBSTR(fecha_gasto, 4, 2) = STRFTIME('%m', 'now') -- Filtrar por el mes actual\n" +
                "    AND SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY \n" +
                "    categoria_principal\n" +
                "ORDER BY \n" +
                "    conteo_repeticiones DESC;\n", null)
        val conteo = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            conteo.add(cursor.getInt(1))
        }
        cursor.close()
        return conteo
    }
    fun leerCantidadVecesCategoriasMesActualNombre(db: SQLiteDatabase): MutableList<String> {
        val cursor = db.rawQuery("SELECT \n" +
                "    categoria_principal, \n" +
                "    COUNT(*) AS conteo_repeticiones,\n" +
                "    SUM(cantidad_gasto) AS total_gasto\n" +
                "FROM \n" +
                "    gastos\n" +
                "WHERE \n" +
                "    SUBSTR(fecha_gasto, 4, 2) = STRFTIME('%m', 'now') -- Filtrar por el mes actual\n" +
                "    AND SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY \n" +
                "    categoria_principal\n" +
                "ORDER BY \n" +
                "    conteo_repeticiones DESC;\n", null)
        val categorias = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val categoria = cursor.getString(0)
            categorias.add(categoria)

        }
        cursor.close()

        return categorias
    }

    fun leerCantidadVecesCategorias(db: SQLiteDatabase): MutableList<Int>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, COUNT(*) AS conteo_repeticiones\n" +
                "FROM gastos\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY conteo_repeticiones DESC;", null)
        val conteo = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            conteo.add(cursor.getInt(1))
        }
        cursor.close()
        return conteo
    }
    fun leerCategoriaMayorGasto(db: SQLiteDatabase): MutableList<String>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, SUM(cantidad_gasto) AS gasto_total\n" +
                "FROM gastos\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY gasto_total DESC;", null)
        val listaCategorias = mutableListOf<String>()
        while (cursor.moveToNext()) {
            listaCategorias.add(cursor.getString(0))
        }
        cursor.close()
        return  listaCategorias
    }
    fun leerSumaEconomicaCategorias(db: SQLiteDatabase): MutableList<Int>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, SUM(cantidad_gasto) AS gasto_total\n" +
                "FROM gastos\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY gasto_total DESC;", null)
        val listaTotalGasto = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            listaTotalGasto.add(cursor.getInt(1))
        }
        cursor.close()
        return listaTotalGasto
    }

    fun leerSumaEconomicaCategoriasAñoActual(db: SQLiteDatabase): MutableList<Int>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, SUM(cantidad_gasto) AS gasto_total\n" +
                "FROM gastos\n" +
                "WHERE SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY gasto_total DESC;", null)
        val listaTotalGasto = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            listaTotalGasto.add(cursor.getInt(1))
        }
        cursor.close()
        return listaTotalGasto
    }

    fun leerCategoriaMayorGastoMesActual(db: SQLiteDatabase): MutableList<String>{
        val cursor = db.rawQuery("SELECT \n" +
                "    categoria_principal, \n" +
                "    SUM(cantidad_gasto) AS gasto_total\n" +
                "FROM \n" +
                "    gastos\n" +
                "WHERE \n" +
                "    SUBSTR(fecha_gasto, 4, 2) = STRFTIME('%m', 'now') -- Filtrar por el mes actual\n" +
                "    AND SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY \n" +
                "    categoria_principal\n" +
                "ORDER BY \n" +
                "    gasto_total DESC;\n", null)
        val listaCategorias = mutableListOf<String>()
        while (cursor.moveToNext()) {
            listaCategorias.add(cursor.getString(0))
        }
        cursor.close()
        return  listaCategorias
    }
    fun leerSumaEconomicaCategoriaMesActual(db: SQLiteDatabase): MutableList<Int>{
        val cursor = db.rawQuery("SELECT \n" +
                "    categoria_principal, \n" +
                "    SUM(cantidad_gasto) AS gasto_total\n" +
                "FROM \n" +
                "    gastos\n" +
                "WHERE \n" +
                "    SUBSTR(fecha_gasto, 4, 2) = STRFTIME('%m', 'now') -- Filtrar por el mes actual\n" +
                "    AND SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY \n" +
                "    categoria_principal\n" +
                "ORDER BY \n" +
                "    gasto_total DESC;", null)
        val listaTotalGasto = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            listaTotalGasto.add(cursor.getInt(1))
        }
        cursor.close()
        return listaTotalGasto
    }

    fun leerCategoriaMayorGastoAñoActual(db: SQLiteDatabase): MutableList<String>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, SUM(cantidad_gasto) AS gasto_total\n" +
                "FROM gastos\n" +
                "WHERE SUBSTR(fecha_gasto, 7) = STRFTIME('%Y', 'now') -- Filtrar por el año actual\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY gasto_total DESC;", null)
        val listaCategorias = mutableListOf<String>()
        while (cursor.moveToNext()) {
            listaCategorias.add(cursor.getString(0))
        }
        cursor.close()
        return  listaCategorias
    }

    //Extrae todas las vategorias con su conteo y su gasto total.
    fun extraerListadoCategoriasTotal(db: SQLiteDatabase): MutableList<String>{
        val cursor = db.rawQuery("" +
                "SELECT categoria_principal, COUNT(*) AS conteo_repeticiones, SUM(cantidad_gasto) AS total_gasto\n" +
                "FROM gastos\n" +
                "GROUP BY categoria_principal\n" +
                "ORDER BY conteo_repeticiones DESC;", null)
        val listaCategorias = mutableListOf<String>()
        while (cursor.moveToNext()) {
            listaCategorias.add(cursor.getString(0))
        }
        cursor.close()
        return  listaCategorias
    }

    fun getDiaIngresoSalario(db: SQLiteDatabase): Int{
        val cursor = db.rawQuery("SELECT dia_ingreso_salario FROM datos_personales", null)
        if (cursor.moveToFirst()) {
            return cursor.getInt(0)
        } else {
            return -1
        }
        cursor.close()
    }
    fun getCopiaSeguridad(db: SQLiteDatabase): Int{
        val cursor = db.rawQuery("SELECT copia_seguridad FROM datos_personales", null)
        if (cursor.moveToFirst()) {
            return cursor.getInt(0)
        } else {
            return 3
        }
        cursor.close()
    }

    fun getCorreoElectronico(db: SQLiteDatabase): String?{
        val cursor = db.rawQuery("SELECT correo_electronico FROM datos_personales", null)
        if (cursor.moveToFirst()) {
            return cursor.getString(0)
        } else {
            return null
        }
        cursor.close()
    }


}