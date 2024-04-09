package com.example.moneyminder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moneyminder.databinding.ActivityMainBinding
import com.example.moneyminder.databinding.ActivityResumenGastosBinding

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


        binding.iBtnCrearGasto.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.iBtnStatistics.id ->{ startActivity(Intent (this, ResumenGastos::class.java))}
            binding.iBtnAlert.id ->{ startActivity(Intent (this, PanelControlAlarma::class.java))}
            binding.iBtnUser.id ->{ startActivity(Intent (this, VerDatosPersonales::class.java))}
            binding.iBtnCrearGasto.id ->{ startActivity(Intent (this, FormularioNuevoGasto::class.java))}
        }
    }
}