package com.example.moneyminder

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityListarCategoriasBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb

class ListarCategorias : AppCompatActivity(), OnClickListener {


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
        var crearDb = CrearDb(this)
        val db = crearDb.writableDatabase

        //binding.categorias.text = LeerDatosDb().leerVecesCategoriasGastos(db)[0]

        var contador = 0
        while (true){
            try {
                binding.categorias.text = binding.categorias.text.toString() + "\n\n" + "Categoría: " +LeerDatosDb().leerVecesCategoriasGastos(db)[contador] + " | Registros: " + LeerDatosDb().leerTotalVecesCategoriasGastos(db)[contador] + " | Total: " + LeerDatosDb().leerTotalGastoCategoriasGastos(db)[contador] + "€"
            }catch (e: Exception){
                break
            }

            contador++
        }


        binding.iBtnStatistics.setOnClickListener(this)
        binding.iBtnAlert.setOnClickListener(this)
        binding.iBtnUser.setOnClickListener(this)
        binding.iBtnCrearGasto.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.iBtnStatistics.id ->{PasarVentanas(this, "ResumenGastos")}
            binding.iBtnAlert.id ->{PasarVentanas(this, "PanelControlAlarma")}
            binding.iBtnUser.id ->{PasarVentanas(this, "VerDatosUsuario")}
            binding.iBtnCrearGasto.id ->{PasarVentanas(this, "FormularioNuevoGasto")}
        }
    }
}