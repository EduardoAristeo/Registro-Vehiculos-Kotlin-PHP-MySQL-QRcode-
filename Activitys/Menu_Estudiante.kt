package com.example.developerlalo.gestveih

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Menu_Estudiante : AppCompatActivity() {

    var idUsuario :Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu__estudiante)
        recibirDatos()
    }

    fun agregarVehiculo(v:View){
        var AgregarV_Intent = Intent(this,Agregar_Vehiculo::class.java)
        AgregarV_Intent.putExtra("idUsuario",idUsuario)
        startActivity(AgregarV_Intent)

    }

    fun misVehiculos(v:View){
        var AgregarV_Intent = Intent(this,Mostrar_Vehiculos::class.java)
        AgregarV_Intent.putExtra("idUsuario",idUsuario)
        startActivity(AgregarV_Intent)

    }

    fun recibirDatos(){
        var leer:Bundle=getIntent().extras
        idUsuario=leer.getInt("idUsuario")

        Toast.makeText(this,"$idUsuario",Toast.LENGTH_SHORT).show()
    }



}
