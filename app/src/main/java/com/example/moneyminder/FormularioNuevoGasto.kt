package com.example.moneyminder

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityFormularioNuevoGastoBinding

class FormularioNuevoGasto : AppCompatActivity(), OnClickListener {

        private lateinit var binding: ActivityFormularioNuevoGastoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormularioNuevoGastoBinding.inflate(layoutInflater)
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
        binding.btnGuardar.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.iBtnStatistics.id ->{PasarVentanas(this, "ResumenGastos")}
            binding.iBtnAlert.id ->{PasarVentanas(this, "PanelControlAlarma")}
            binding.iBtnUser.id ->{PasarVentanas(this, "VerDatosUsuario")}
            binding.iBtnCrearGasto.id ->{PasarVentanas(this, "FormularioNuevoGasto")}
            binding.btnGuardar.id -> {
                //Extraemos los datos del formulario
                val cantidad_gasto = binding.inputCantidadGasto.text.toString()
                val categoria_principal = binding.catGasto.selectedItem.toString()
                val descripcion = binding.editTextDescripcion.text.toString()
                val ingresoGasto = findViewById<RadioButton>(binding.rGroupIngresoGasto.checkedRadioButtonId).text.toString()


            }
        }
    }
}
