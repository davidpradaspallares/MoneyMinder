package com.example.moneyminder

import android.content.ContentValues.TAG
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.db.ComprobarDb
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.Firebase.ConexionFirebase
import com.example.moneyminder.db.LeerDatosDb
import com.example.moneyminder.model_de_datos.Gastos
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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


        //CrearDb(this)

        //Comprobamos si la base de datos existe y tiene los datos requeridos.
        val comprobarDb = ComprobarDb(this)
        val resultadoComprobacion = comprobarDb.comprobarDb()

        if(resultadoComprobacion != null) {
            val intent = Intent(this, FormularioNuevoGasto::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "No hay datos en la base de datos", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FormularioDatosPersonales::class.java)
            startActivity(intent)
            finish()
        }



    }
}