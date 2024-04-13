package com.example.moneyminder

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.i18n.DateTimeFormatter
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityFormularioNuevoGastoBinding
import com.example.moneyminder.db.IngresarDatosDb
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FormularioNuevoGasto : AppCompatActivity(), OnClickListener {

    private lateinit var fechaSeleccionada: Date
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
        fechaSeleccionada = calendario.time
        fecha = DatePickerDialog.OnDateSetListener{ datepicker, year, month, day ->
            calendario.set(Calendar.YEAR, year)
            calendario.set(Calendar.MONTH, month)
            calendario.set(Calendar.DAY_OF_YEAR, day)
            fechaSeleccionada = calendario.time
            actualizarFecha(calendario)
        }

        binding.iBtnStatistics.setOnClickListener(this)
        binding.iBtnAlert.setOnClickListener(this)
        binding.iBtnUser.setOnClickListener(this)
        binding.iBtnCrearGasto.setOnClickListener(this)
        binding.btnGuardar.setOnClickListener(this)
        binding.lLayoutFechaGasto.setOnClickListener(this)
        binding.rBtnIngreso.setOnClickListener(this)
        binding.radioButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.iBtnStatistics.id ->{PasarVentanas(this, "ResumenGastos")}
            binding.iBtnAlert.id ->{PasarVentanas(this, "PanelControlAlarma")}
            binding.iBtnUser.id ->{PasarVentanas(this, "VerDatosUsuario")}
            binding.iBtnCrearGasto.id ->{PasarVentanas(this, "FormularioNuevoGasto")}
            //Creamos la diferenciación visual entre ingreso y gasto
            binding.rBtnIngreso.id -> {
                val miColorVerde = ContextCompat.getColor(this, R.color.green)
                binding.tableLayout.setBackgroundColor(miColorVerde)
                binding.btnGuardar.setBackgroundColor(miColorVerde)
                binding.inputCantidadGasto.setBackgroundColor(miColorVerde)
                binding.grupoIngreso.visibility = View.VISIBLE
                binding.grupoGasto.visibility = View.GONE
            }
            binding.radioButton.id -> {
                val miColor = ContextCompat.getColor(this, R.color.red)
                binding.tableLayout.setBackgroundColor(miColor)
                binding.btnGuardar.setBackgroundColor(miColor)
                binding.inputCantidadGasto.setBackgroundColor(miColor)
                binding.grupoIngreso.visibility = View.GONE
                binding.grupoGasto.visibility = View.VISIBLE
            }
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
                if(binding.inputCantidadGasto.text.toString() == ""){binding.inputCantidadGasto.setText("0")}
                //Extraemos los datos del formulario
                val cantidad_gasto = binding.inputCantidadGasto.text.toString()
                val categoria_principal = binding.catGasto.selectedItem.toString()
                val descripcion = binding.editTextDescripcion.text.toString()
                val ingresoGasto = findViewById<RadioButton>(binding.rGroupIngresoGasto.checkedRadioButtonId).text.toString()
                val metodoPago = findViewById<RadioButton>(binding.rGroupMetodoPago.checkedRadioButtonId).text.toString()
                val procedenciaIngreso = binding.procedenciaIngreso.selectedItem.toString()
                val metodoIngreso = findViewById<RadioButton>(binding.rGroupMetodoIngreso.checkedRadioButtonId).text.toString()

                var ingresarDatosDb = IngresarDatosDb(this)
                //Diferenciamos entre ingreso y gasto Y Ingresamos los datos en la base de datos.
                when(ingresoGasto){
                    "INGRESO" -> {}
                    "GASTO" -> {ingresarDatosDb.insertarDatosNuevoGasto(cantidad_gasto.toDouble(),categoria_principal,descripcion,metodoPago,fechaSeleccionada)}
                }

            }
        }
    }

    //Actualiza la fecha en el "textFecha"
    private fun actualizarFecha(calendar: Calendar){
        val formatoFecha = "dd-MM-yyyy"
        val formatoSimple = SimpleDateFormat(formatoFecha, Locale.ENGLISH)
        binding.textFecha.text = formatoSimple.format(calendar.time)
    }
}
