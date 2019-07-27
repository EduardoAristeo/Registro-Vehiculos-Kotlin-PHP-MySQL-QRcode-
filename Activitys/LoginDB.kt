package com.example.developerlalo.gestveih

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class LoginDB : AppCompatActivity() {

     var TypeLogin:Int=0
    lateinit var txtusr:TextView
    lateinit var txtpass: TextView
    lateinit var btnlogin:Button
    lateinit var btncr:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_db)


        txtusr=findViewById(R.id.ET_Marca2)
        txtpass=findViewById(R.id.ET_Marca3)
        btnlogin=findViewById(R.id.BTTN_Registrar2)
        btncr=findViewById(R.id.BTTN_AgregarFoto2)



        btnlogin.setOnClickListener {
            login()
        }

    }


    fun login(){
        val queue = Volley.newRequestQueue(this)
        val usuario=txtusr.text.toString()
        val password=txtpass.text.toString()
        //Toast.makeText(this,usuario+" "+ password,Toast.LENGTH_SHORT).show()
        var url="http://mgf.jpsistemas.solutions/autentificaralmC.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj: JSONObject = JSONObject(response)
                    Toast.makeText(this,jsonObj.getString("mensaje"), Toast.LENGTH_SHORT).show()
                    if (jsonObj.getString("error")=="false"){

                        //Toast.makeText(this,jsonObj.getString("id"),Toast.LENGTH_SHORT).show()
                        var idUsuario:Int=jsonObj.getString("mensaje").toString().toInt()

                        var Estudiante=Intent(this, Menu_Estudiante::class.java)
                        Estudiante.putExtra("idUsuario",idUsuario)
                        startActivity(Estudiante)
                    }
                    else{
                        Toast.makeText(this,"CONTRASEÑA O USUARIO INCORRECTO", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: JSONException){
                    Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {

            })
        {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String,String>{
                val params = HashMap<String,String>()
                params.put("usuario", usuario)
                params.put("password", password)
                return params
            }
        }
        queue.add(StringReq)
    }

    fun CrearCuenta(v:View){
        var Estudiante=Intent(this, Register_Estudiante::class.java)
        startActivity(Estudiante)

    }

    }






