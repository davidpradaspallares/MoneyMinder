package com.example.moneyminder

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityFormularioDatosPersonalesBinding
import com.example.moneyminder.db.CrearDb
import com.example.moneyminder.db.Firebase.ConexionFirebase
import com.example.moneyminder.db.IngresarDatosDb
import com.example.moneyminder.model_de_datos.Usuario

class FormularioDatosPersonales : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioDatosPersonalesBinding

    var nombre = ""
    var apellidos = ""
    var email = ""
    var numeroTelefono = ""
    var salarioMensual = ""
    var ingresoSalario = ""
    var copiaSeguridad = false
    var contrasena = ""


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

        //Llamamos la funcion de Java para crear la base de dato y suestructura.
        val creardB = CrearDb(this)
        val db = creardB.writableDatabase


        //Evento para el boton de guardar.
        binding.btnGuardar.setOnClickListener(){
            //Recogemos los datos
            this.nombre = binding.inputName.text.toString()
            this.apellidos = binding.inputApellidos.text.toString()
            this.email = binding.inputEmail.text.toString()
            this.numeroTelefono = binding.inputTelefono.text.toString()
            this.salarioMensual = binding.inputSalarioMensual.text.toString()
            this.ingresoSalario = binding.inputDiaIngreso.text.toString()
            this.copiaSeguridad = binding.switchCopiaSeguridad.isChecked
            this.contrasena = binding.inputContrasena.text.toString()

            //Si no tienen un dato todas las variables da error al insertar en la base de datos PROVISIONAL
            if(this.email.length<1){this.email = "-"}
            if(this.numeroTelefono.length<1){this.numeroTelefono = "0"}
            if(this.salarioMensual.length<1){this.salarioMensual = "0.00"}
            if(this.ingresoSalario.length<1){this.ingresoSalario = "0"}

            //Comprobamos si switchCopiaSeguridad esta activado
            if(this.copiaSeguridad){
                usuarioConCopiaDeSeguridad(db)
            }else{
                usuarioSinCopiaSeguridad(db)
            }
        }
        binding.switchCopiaSeguridad.setOnClickListener(){
            //Comprobamos si switchCopiaSeguridad esta activado
            if(binding.switchCopiaSeguridad.isChecked){
                binding.inputContrasenaL.visibility = View.VISIBLE;
            }else{
                binding.inputContrasenaL.visibility = View.GONE;
            }
        }
    }

    fun usuarioSinCopiaSeguridad( db: SQLiteDatabase) {
        //Comprobamos que el usaurio introduzca los datos obligatorios y que la base de datos se cree correctamente.
        if(!(this.nombre != "" && this.apellidos != "")){
            Toast.makeText(this, "ERROR - RECUERDA INTRODUCIR TODOS LOS DATOS", Toast.LENGTH_LONG).show()
        }else{
            if(db != null){
                ingresarDatosNuevoUsuario(Usuario(
                    this.nombre,
                    this.apellidos,
                    this.email,
                    this.numeroTelefono.toInt(),
                    this.salarioMensual.toDouble(),
                    this.ingresoSalario.toInt(),
                    this.copiaSeguridad))
            }else{
                Toast.makeText(this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun usuarioConCopiaDeSeguridad( db: SQLiteDatabase) {
        //Comprobamos que el usaurio introduzca los datos obligatorios y que la base de datos se cree correctamente.
        if(!(this.nombre != "" && this.apellidos != "" && this.email != "" && this.contrasena != "")){
            Toast.makeText(this, "ERROR - RECUERDA INTRODUCIR TODOS LOS DATOS (INCLUIDO EMAIL)", Toast.LENGTH_LONG).show()
        }else{
            if(db != null){
                //Ingresamos usuario en la base de datos interna
                ingresarDatosNuevoUsuario(Usuario(
                    this.nombre,
                    this.apellidos,
                    this.email,
                    this.numeroTelefono.toInt(),
                    this.salarioMensual.toDouble(),
                    this.ingresoSalario.toInt(),
                    this.copiaSeguridad))
                //Ingresamos usuario en la base de datos externa
                ConexionFirebase(this).ingresarUsuario(
                    Usuario(
                        this.nombre,
                        this.apellidos,
                        this.email,
                        this.numeroTelefono.toInt(),
                        this.salarioMensual.toDouble(),
                        this.ingresoSalario.toInt(),
                        this.copiaSeguridad,
                        this.contrasena))
            }else{
                Toast.makeText(this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun ingresarDatosNuevoUsuario(usuario: Usuario){
        //Ingresamos los datos del usuario en su tabla.
        var ingresarDatosDb = IngresarDatosDb(this)
        ingresarDatosDb.insertarDatosUsuario(usuario.nombre,
            usuario.apellidos,
            usuario.correoElectronico,
            usuario.telefono,
            usuario.salarioMensual,
            usuario.diaIngresoSalario,
            usuario.copiaSeguridad)
        //Pasamos al main
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}