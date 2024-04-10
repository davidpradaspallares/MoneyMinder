package com.example.moneyminder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moneyminder.db.CrearDb;

import java.util.Date;

public class IngresarDatosDb extends CrearDb {
    Context context;
    public IngresarDatosDb(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarDatosUsuario(String nombre, String apellidos, String correo_electronico, int telefono, double salario_mensual, int dia_ingreso_salario){
        CrearDb crearDb = new CrearDb(context);
        SQLiteDatabase db = crearDb.getWritableDatabase();



        //Insertamos el registro
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("apellidos", apellidos);
        valores.put("correo_electronico", correo_electronico);
        valores.put("telefono", telefono);
        valores.put("salario_mensual", salario_mensual);
        valores.put("dia_ingreso_salario", dia_ingreso_salario);

        long id = db.insert("datos_personales", null, valores);
        return id;
    }

    /*public long insertarDatosNuevoGasto(double contidad_gasto, String categoria_principal, String categoria_secundaria, String descripcion, String metodo_pago, Date fecha_gasto){

    }*/


}
