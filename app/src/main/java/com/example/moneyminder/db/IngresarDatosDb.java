package com.example.moneyminder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.moneyminder.db.CrearDb;

import java.text.SimpleDateFormat;
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

    public long ingresarPrueba(){
        CrearDb crearDb = new CrearDb(context);
        SQLiteDatabase db = crearDb.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", "David");
        valores.put("apellidos", "Pradas Pallares");
        valores.put("correo_electronico", "david28122000@gmail.com");
        valores.put("telefono", 683555250);
        valores.put("salario_mensual", 1090.53);
        valores.put("dia_ingreso_salario", 10);

        long id = db.insert("datos_personales", null, valores);
        return id;
    }

    public long insertarDatosNuevoGasto(double cantidad_gasto, String categoria_principal, String descripcion, String metodo_pago, Date fecha_gasto){
        CrearDb crearDb = new CrearDb(context);
        SQLiteDatabase db = crearDb.getWritableDatabase();

        //Insertamos los registros
        ContentValues valores = new ContentValues();
        valores.put("cantidad_gasto", cantidad_gasto);
        valores.put("categoria_principal", categoria_principal);
        valores.put("metodo_pago", metodo_pago);
        valores.put("descripcion", descripcion);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = sdf.format(fecha_gasto);
        valores.put("fecha_gasto", fechaFormateada);

        long id = db.insert("gastos", null, valores);
        return id;
    }

    public long insertarDatosNuevoIngreso(double cantidad_ingreso, String procedencia_ingreso, String descripcion, String metodo_ingreso, Date fecha_ingreso){
        CrearDb crearDb = new CrearDb(context);
        SQLiteDatabase db = crearDb.getWritableDatabase();

        //Insertamos los registros
        ContentValues valores = new ContentValues();
        valores.put("cantidad_ingreso", cantidad_ingreso);
        valores.put("procedencia_ingreso", procedencia_ingreso);
        valores.put("metodo_ingreso", metodo_ingreso);
        valores.put("descripcion", descripcion);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = sdf.format(fecha_ingreso);
        valores.put("fecha_ingreso", fechaFormateada);

        long id = db.insert("ingresos", null, valores);
        return id;
    }


}
