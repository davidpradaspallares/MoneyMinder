package com.example.moneyminder

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityFormularioNuevaAlarmaBinding
import com.example.moneyminder.databinding.ActivityFormularioNuevoGastoBinding

class FormularioNuevaAlarma : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityFormularioNuevaAlarmaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormularioNuevaAlarmaBinding.inflate(layoutInflater)
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