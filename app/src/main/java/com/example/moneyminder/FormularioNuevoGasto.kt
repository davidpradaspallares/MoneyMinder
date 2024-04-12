package com.example.moneyminder

import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FormularioNuevoGasto : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityFormularioNuevoGastoBinding
    val calendario = Calendar.getInstance()
    var fecha: DatePickerDialog.OnDateSetListener? = null;
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
        actualizarFecha(calendario)
        fecha = DatePickerDialog.OnDateSetListener{ datepicker, year, month, day ->
            calendario.set(Calendar.YEAR, year)
            calendario.set(Calendar.MONTH, month)
            calendario.set(Calendar.DAY_OF_YEAR, day)
            actualizarFecha(calendario)
        }

        binding.iBtnStatistics.setOnClickListener(this)
        binding.iBtnAlert.setOnClickListener(this)
        binding.iBtnUser.setOnClickListener(this)
        binding.iBtnCrearGasto.setOnClickListener(this)
        binding.btnGuardar.setOnClickListener(this)
        binding.lLayoutFechaGasto.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.iBtnStatistics.id ->{PasarVentanas(this, "ResumenGastos")}
            binding.iBtnAlert.id ->{PasarVentanas(this, "PanelControlAlarma")}
            binding.iBtnUser.id ->{PasarVentanas(this, "VerDatosUsuario")}
            binding.iBtnCrearGasto.id ->{PasarVentanas(this, "FormularioNuevoGasto")}
            binding.lLayoutFechaGasto.id -> {

                DatePickerDialog(
                    this,
                    fecha,
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)

                ).show()
            }
            binding.btnGuardar.id -> {
                //Extraemos los datos del formulario
                val cantidad_gasto = binding.inputCantidadGasto.text.toString()
                val categoria_principal = binding.catGasto.selectedItem.toString()
                val descripcion = binding.editTextDescripcion.text.toString()
                val ingresoGasto = findViewById<RadioButton>(binding.rGroupIngresoGasto.checkedRadioButtonId).text.toString()
                val fechaIngresoGasto = binding.textFecha.text.toString()


            }
        }
    }

    private fun actualizarFecha(calendar: Calendar){
        val formatoFecha = "dd-MM-yyyy"
        val formatoSimple = SimpleDateFormat(formatoFecha, Locale.ENGLISH)
        binding.textFecha.text = formatoSimple.format(calendar.time)
    }
}
