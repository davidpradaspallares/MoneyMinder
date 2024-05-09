package com.example.moneyminder

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityMainBinding
import com.example.moneyminder.databinding.ActivityResumenGastosBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb
import com.example.moneyminder.model_de_datos.Gastos
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ResumenGastos : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityResumenGastosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResumenGastosBinding.inflate(layoutInflater)
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
        binding.btnListarGastos.setOnClickListener(this)
        binding.btnFiltroPorDefecto.setOnClickListener(this)
        binding.btnFiltroEsteAno.setOnClickListener(this)
        binding.btnTodosLosRegistros.setOnClickListener(this)
        binding.btnListarCategorias.setOnClickListener(this)
        //Toast.makeText(this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show()
        rellenarFormPorDefecto()
        binding.btnFiltroPorDefecto.background = ColorDrawable(Color.GRAY)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.iBtnStatistics.id -> {
                PasarVentanas(this, "ResumenGastos")
            }

            binding.iBtnAlert.id -> {
                PasarVentanas(this, "PanelControlAlarma")
            }

            binding.iBtnUser.id -> {
                PasarVentanas(this, "VerDatosUsuario")
            }

            binding.iBtnCrearGasto.id -> {
                PasarVentanas(this, "FormularioNuevoGasto")
            }

            binding.btnListarGastos.id -> {
                startActivity(Intent(this, ListarIngresosGastos::class.java))
            }

            binding.btnListarCategorias.id -> {
                startActivity(Intent(this, ListarCategorias::class.java))
            }

            binding.btnFiltroPorDefecto.id -> {
                binding.btnFiltroPorDefecto.setTextColor(getColor(R.color.gray))
                binding.btnFiltroEsteAno.setTextColor(getColor(R.color.black))
                binding.btnTodosLosRegistros.setTextColor(getColor(R.color.black))
                rellenarFormPorDefecto()
            }

            binding.btnFiltroEsteAno.id -> {
                binding.btnFiltroPorDefecto.setTextColor(getColor(R.color.black))
                binding.btnFiltroEsteAno.setTextColor(getColor(R.color.gray))
                binding.btnTodosLosRegistros.setTextColor(getColor(R.color.black))
                binding.textGastoTotal.text =
                    SacarDatosResumenDatos(this).sumarGastosAñoActual().toString()
                binding.textGastoMasRc1.text =
                    SacarDatosResumenDatos(this).catMasRecurrenteEsteAño()[0]
                binding.textGastoMasRc2.text =
                    SacarDatosResumenDatos(this).catMasRecurrenteEsteAño()[1]
                binding.textGastoMasRc3.text =
                    SacarDatosResumenDatos(this).catMasRecurrenteEsteAño()[2]
                binding.textGastoCat1.text = SacarDatosResumenDatos(this).catMasGastoAñoActual()[0]
                binding.textGastoCat2.text = SacarDatosResumenDatos(this).catMasGastoAñoActual()[1]
                binding.textGastoCat3.text = SacarDatosResumenDatos(this).catMasGastoAñoActual()[2]
                binding.textDineroRestante.text = "NO DISPONIBLE"

            }

            binding.btnTodosLosRegistros.id -> {
                binding.btnFiltroPorDefecto.setTextColor(getColor(R.color.black))
                binding.btnFiltroEsteAno.setTextColor(getColor(R.color.black))
                binding.btnTodosLosRegistros.setTextColor(getColor(R.color.gray))
                binding.textGastoTotal.text =
                    SacarDatosResumenDatos(this).sumarGastosTotal().toString()
                binding.textGastoMasRc1.text = SacarDatosResumenDatos(this).catMasRecurrente()[0]
                binding.textGastoMasRc2.text = SacarDatosResumenDatos(this).catMasRecurrente()[1]
                binding.textGastoMasRc3.text = SacarDatosResumenDatos(this).catMasRecurrente()[2]
                binding.textGastoCat1.text = SacarDatosResumenDatos(this).catMasGasto()[0]
                binding.textGastoCat2.text = SacarDatosResumenDatos(this).catMasGasto()[1]
                binding.textGastoCat3.text = SacarDatosResumenDatos(this).catMasGasto()[2]
                binding.textDineroRestante.text = "NO DISPONIBLE"
            }
        }
    }

    fun rellenarFormPorDefecto(){
        binding.textGastoTotal.text = String.format("%.2f",SacarDatosResumenDatos(this).sumarGastosTuMes()).toString() + "€"
        binding.textDineroRestante.text =String.format("%.2f",SacarDatosResumenDatos(this).DineroRestanteMes()).toString()
        binding.textGastoMasRc1.text = SacarDatosResumenDatos(this).catMasRecurrente()[0]
        binding.textGastoMasRc2.text = SacarDatosResumenDatos(this).catMasRecurrente()[1]
        binding.textGastoMasRc3.text = SacarDatosResumenDatos(this).catMasRecurrente()[2]
        binding.textGastoCat1.text = SacarDatosResumenDatos(this).catMasGasto()[0]
        binding.textGastoCat2.text = SacarDatosResumenDatos(this).catMasGasto()[1]
        binding.textGastoCat3.text = SacarDatosResumenDatos(this).catMasGasto()[2]
    }
}