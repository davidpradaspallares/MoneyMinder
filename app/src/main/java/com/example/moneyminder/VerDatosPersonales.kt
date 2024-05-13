package com.example.moneyminder

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityVerDatosPersonalesBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.LeerDatosDb

class VerDatosPersonales : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityVerDatosPersonalesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVerDatosPersonalesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Asignamos eventos
        binding.iBtnStatistics.setOnClickListener(this)
        binding.iBtnAlert.setOnClickListener(this)
        binding.iBtnUser.setOnClickListener(this)
        binding.iBtnCrearGasto.setOnClickListener(this)

        cambiarDatos()
    }

    fun cambiarDatos(){
        val crearDb = CrearDb(this)
        val db = crearDb.readableDatabase

        var nombre: String = "null"
        var apellidos: String = "null"
        var correoElectronico: String = "null"
        var telefono = 0
        var diaIngresoSalario = 0
        var salarioMensual = 0.0
        var copiaSeguridad = 3

        val cursor = db.rawQuery("SELECT * FROM datos_personales", null)

        if (cursor.moveToFirst()) {
            do {
                nombre = cursor.getString(0)
                apellidos = cursor.getString(1)
                correoElectronico = cursor.getString(2)
                telefono = cursor.getInt(3)
                salarioMensual = cursor.getDouble(4)
                diaIngresoSalario = cursor.getInt(5)
                copiaSeguridad = cursor.getInt(6)
            } while (cursor.moveToNext())
        }
        cursor.close()

        if(copiaSeguridad == 0){
            binding.textCopiaSeguridad.text = "No (Datos sin respaldo)"
        }else if (copiaSeguridad == 1){
            binding.textCopiaSeguridad.text = "Si (Datos con respaldo)"
        }else{
            Toast.makeText(applicationContext, "ERROR EN COPIA DE SEGURIDAD", Toast.LENGTH_SHORT).show()
        }

        binding.textNombre.text = nombre
        binding.textApellidos.text = apellidos
        binding.textEmail.text = correoElectronico
        binding.textTelefono.text = telefono.toString()
        binding.textSalarioMensual.text = salarioMensual.toString() + "€"
        binding.textDiaDePago.text = diaIngresoSalario.toString()
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

