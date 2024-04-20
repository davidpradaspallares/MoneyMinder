package com.example.moneyminder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityFormularioDatosPersonalesBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.IngresarDatosDb

class FormularioDatosPersonales : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioDatosPersonalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityFormularioDatosPersonalesBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Evento para el boton de guardar.
        binding.btnGuardar.setOnClickListener(){
            //Recogemos los datos
            var nombre = binding.inputName.text.toString()
            var apellidos = binding.inputApellidos.text.toString()
            var email = binding.inputEmail.text.toString()
            var numeroTelefono = binding.inputTelefono.text.toString()
            var salarioMensual = binding.inputSalarioMensual.text.toString()
            var ingresoSalario = binding.inputDiaIngreso.text.toString()
            //Llamamos la funcion de Java para crear la base de dato y suestructura.
            val creardB = CrearDb(this)
            val db = creardB.writableDatabase

            //Si no tienen un dato todas las variables da error al insertar en la base de datos PROVISIONAL
            if(email.length<1){email = "-"}
            if(numeroTelefono.length<1){numeroTelefono = "0"}
            if(salarioMensual.length<1){salarioMensual = "0.00"}
            if(ingresoSalario.length<1){ingresoSalario = "0"}

            //Comprobamos que el usaurio introduzca los datos obligatorios y que la base de datos se cree correctamente.
            if(!(nombre != "" && apellidos != "")){
                Toast.makeText(this, "ERROR - RECUERDA INTRODUCIR TODOS LOS DATOS", Toast.LENGTH_LONG).show()
            }else{
                if(db != null){

                    //Ingresamos los datos del usuario en su tabla.
                    var ingresarDatosDb = IngresarDatosDb(this)
                    //ingresarDatosDb.insertarDatosUsuario(nombre,apellidos,email,numeroTelefono.toInt(),salarioMensual.toDouble(),ingresoSalario.toInt())
                    ingresarDatosDb.ingresarPrueba()

                    //Pasamos al main
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show()
                }
            }
        }



    }
}