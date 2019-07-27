package com.example.developerlalo.gestveih

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class LoginDB_Vigilante : AppCompatActivity() {

    var TypeLogin:Int=0
    lateinit var txtusr: EditText
    lateinit var txtpass: EditText
    lateinit var btnlogin: Button
    lateinit var btncr: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_db__vigilante)
        txtusr=findViewById(R.id.ET_USUARIO)
        txtpass=findViewById(R.id.ET_CONTRA)
        btnlogin=findViewById(R.id.BTTN_eNTRAR)




    }

    fun login(v:View){
        val queue = Volley.newRequestQueue(this)
        val usuario=txtusr.text.toString()
        val password=txtpass.text.toString()
        //Toast.makeText(this,usuario+" "+ password,Toast.LENGTH_SHORT).show()
        var url="http://mgf.jpsistemas.solutions/autentificar.php"

        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                Toast.makeText(this,"AQUI BIEN",Toast.LENGTH_SHORT).show()
                try {
                    val jsonObj: JSONObject = JSONObject(response)
                    Toast.makeText(this,jsonObj.getString("mensaje"), Toast.LENGTH_SHORT).show()
                    if (jsonObj.getString("error")=="false"){

                        //Toast.makeText(this,jsonObj.getString("id"),Toast.LENGTH_SHORT).show()
                        var Estudiante=Intent(this, Menu_Vigilante::class.java)

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
}
