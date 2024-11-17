package com.example.moneyminder.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ComprobarDb extends CrearDb {

    Context context;
    public ComprobarDb(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    @SuppressLint("Range")
    public String comprobarDb() {
        String nombre = null;
        String apellidos = null;

        // Se crea la instancia de la base de datos
        CrearDb crearDb = new CrearDb(context);
        SQLiteDatabase db = crearDb.getReadableDatabase();

        Cursor cursorElementos = null;

        try {
            // Se ejecuta la consulta SQL para obtener el nombre y los apellidos
            cursorElementos = db.rawQuery("SELECT nombre, apellidos FROM datos_personales", null);

            // Se verifica si hay alguna fila en el cursor
            if (cursorElementos.moveToFirst()) {
                // Se extraen el nombre y los apellidos de las columnas correspondientes
                nombre = cursorElementos.getString(cursorElementos.getColumnIndex("nombre"));
                apellidos = cursorElementos.getString(cursorElementos.getColumnIndex("apellidos"));
            }
        } finally {
            // Se asegura de cerrar el cursor para liberar recursos
            if (cursorElementos != null) {
                cursorElementos.close();
            }
        }

        // Se verifica si se ha encontrado algún dato en la base de datos
        if (nombre != null && apellidos != null) {
            // Si se han encontrado datos, se asigna el valor "nombre" a la variable nombre
            nombre = "nombre";
        }

        // Se retorna el nombre, que puede ser "nombre" si se encontraron datos o null si no se encontraron
        return nombre;
    }

}
