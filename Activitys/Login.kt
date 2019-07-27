package com.example.developerlalo.gestveih

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    //SE ENVÍA VALOR 1 PARA GUARDIAS Y VALOR 2 PARA ESTUDIANTES

    fun elegirLoginGuardia(v:View){
        var LoginDB_Intent = Intent(this,LoginDB_Vigilante::class.java)
        LoginDB_Intent.putExtra("TypeLogin",1)
        startActivity(LoginDB_Intent)
    }

    fun elegirLoginEstudiante(v: View){
        var LoginDB_Intent = Intent(this,LoginDB::class.java)
        LoginDB_Intent.putExtra("TypeLogin",2)
        startActivity(LoginDB_Intent)
    }
}
