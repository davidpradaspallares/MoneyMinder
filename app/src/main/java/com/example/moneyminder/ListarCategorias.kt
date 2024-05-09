package com.example.moneyminder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityListarCategoriasBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb

class ListarCategorias : AppCompatActivity() {

    var crearDb = CrearDb(this)
    val db = crearDb.writableDatabase

    private lateinit var binding: ActivityListarCategoriasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListarCategoriasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.categorias.text = LeerDatosDb().leerVecesCategoriasGastos(db)[0]





    }
}