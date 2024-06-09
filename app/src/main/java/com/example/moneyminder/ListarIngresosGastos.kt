package com.example.moneyminder

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyminder.RecicleView.GastosAdapter
import com.example.moneyminder.databinding.ActivityListarIngresosGastosBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb

class ListarIngresosGastos : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityListarIngresosGastosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListarIngresosGastosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.iBtnStatistics.setOnClickListener(this)
        binding.iBtnAlert.setOnClickListener(this)
        binding.iBtnUser.setOnClickListener(this)
        binding.iBtnCrearGasto.setOnClickListener(this)
        binding.iBtnMostrarMas.setOnClickListener(this)

        //val items = listOf("100 | Capricho | Tarjeta | 09-06-2024", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 2", "Elemento 3", "Elemento 4")
        //var crearDb = CrearDb(this)
        //val db = crearDb.writableDatabase
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, LeerDatosDb().leerGastos(db))

        //binding.listaIngresos.adapter = adapter
        iniciarRecicleViewGastos()
    }
    override fun onClick(v: View?) {
        when(v?.id){
            binding.iBtnStatistics.id ->{PasarVentanas(this, "ResumenGastos")}
            binding.iBtnAlert.id ->{PasarVentanas(this, "PanelControlAlarma")}
            binding.iBtnUser.id ->{PasarVentanas(this, "VerDatosUsuario")}
            binding.iBtnCrearGasto.id ->{PasarVentanas(this, "FormularioNuevoGasto")}
            binding.iBtnMostrarMas.id ->{
                //Mostramos o ocultamos los botones de INGRESOS y GASTOS
                if(binding.masBotonesLayout.isVisible){
                    binding.masBotonesLayout.visibility = View.GONE
                }else{
                    binding.masBotonesLayout.visibility = View.VISIBLE
                }
            }
        }
    }
    fun iniciarRecicleViewGastos(){
        var crearDb = CrearDb(this)
        val db = crearDb.writableDatabase

        val recyclerView = findViewById<RecyclerView>(R.id.recicleViewIngresosGastos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GastosAdapter(LeerDatosDb().leerGastos(db))
        db.close()
    }
}