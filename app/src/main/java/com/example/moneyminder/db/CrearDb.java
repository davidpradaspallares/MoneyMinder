package com.example.moneyminder.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CrearDb extends SQLiteOpenHelper {

    private static final int VERSION_DB = 1;
    public CrearDb(@Nullable Context context) {
        super(context, "MoneyMinder", null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creamos las tablas que necesitamos.
        sqLiteDatabase.execSQL("CREATE TABLE datos_personales" + "(" +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "correo_electronico TEXT," +
                "telefono INT," +
                "salario_mensual DOUBLE," +
                "dia_ingreso_salario INT)");
        sqLiteDatabase.execSQL("CREATE TABLE gastos" + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "cantidad_gasto DOUBLE NOT NULL ," +
                "categoria_principal TEXT NOT NULL ," +
                "descripcion TEXT," +
                "fecha_gasto DATE)");
        sqLiteDatabase.execSQL("CREATE TABLE alarmas" + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "categoria_alarma TEXT NOT NULL," +
                "veces_repetir INT NOT NULL," +
                "importe DOUBLE NOT NULL," +
                "dia_alarma INT NOT NULL," +
                "fecha_creacion DATE NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE datos_personales");
        sqLiteDatabase.execSQL("DROP TABLE gastos");
        sqLiteDatabase.execSQL("DROP TABLE alarmas");
        onCreate(sqLiteDatabase);
    }
}
