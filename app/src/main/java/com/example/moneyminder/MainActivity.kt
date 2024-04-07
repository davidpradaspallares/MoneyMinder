package com.example.moneyminder

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.db.ComprobarDb

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Comprobamos si la base de datos existe y tiene los datos requeridos.
        val comprobarDb = ComprobarDb(this)
        val resultadoComprobacion = comprobarDb.comprobarDb()

        if(resultadoComprobacion != null) {
            Toast.makeText(this, resultadoComprobacion, Toast.LENGTH_LONG).show()
            val intent = Intent(this, FormularioNuevoGasto::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "No hay datos en la base de datos", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FormularioDatosPersonales::class.java)
            startActivity(intent)
        }



    }
}