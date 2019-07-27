package com.example.developerlalo.gestveih

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class Register_Estudiante : AppCompatActivity() {

    lateinit var regst: Button
    lateinit var generar: Button
    lateinit var txtpn: TextView
    lateinit var txtsn: TextView
    lateinit var txtap: TextView
    lateinit var txtam: TextView
    lateinit var txtco: TextView
    lateinit var txtcon: TextView
    lateinit var txtconv: TextView

    lateinit var UserNameGenerado:String

    lateinit var UsuarioGenerado:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register__estudiante)

        generar=findViewById(R.id.BTTN_GenerarName)
        txtpn=findViewById(R.id.ET_PrimerNombre)
        txtsn=findViewById(R.id.ET_SegundoNombre)
        txtap=findViewById(R.id.ET_PrimerApellid)
        txtam=findViewById(R.id.ET_SegundoApellido)
        txtco=findViewById(R.id.ET_Email)
        txtcon=findViewById(R.id.ET_Contraseña)
        txtconv=findViewById(R.id.ET_Contraseña2)
        regst=findViewById(R.id.BTTN_CrearCuenta)

        UsuarioGenerado=findViewById(R.id.TV_Username)


        regst.setOnClickListener {
            checkValue()
        }
    }

    fun checkValue(){
        val Contraseñab=txtcon.text.toString()
        val Contraseñav=txtconv.text.toString()
        if(Contraseñab==Contraseñav){
            registrarU()
        }else{
            Toast.makeText(this, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_SHORT).show()
        }
    }

    fun registrarU(){
        val queue= Volley.newRequestQueue(this)
        val PNombre=txtpn.text.toString()
        val SNombre=txtsn.text.toString()
        val PApellido=txtap.text.toString()
        val SApellido=txtam.text.toString()
        val Email=txtco.text.toString()
        val Contraseña=txtcon.text.toString()
        val UsuarioGeneradoListo=UsuarioGenerado.text.toString();

        var url="http://mgf.jpsistemas.solutions/agregarusuario.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj = JSONObject(response)

                    Toast.makeText(this,jsonObj.getString("mensaje"),Toast.LENGTH_SHORT).show()
                    var Estudiante= Intent(this, LoginDB::class.java)
                    startActivity(Estudiante)
                }
                catch (e: JSONException){
                    Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {

            })
        {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String,String>{
                val params = HashMap<String,String>()
                params.put("PNombre", PNombre)
                params.put("SNombre", SNombre)
                params.put("PApellido", PApellido)
                params.put("SApellido", SApellido)
                params.put("Email", Email)
                params.put("Contraseña", Contraseña)
                params.put("Username", UsuarioGeneradoListo)
                return params
            }
        }
        queue.add(StringReq)
    }

    fun GenerarUserName(v:View){

    var PrimerNombre:String=txtpn.text.toString()
    var PrimerApellido:String=txtam.text.toString()
        PrimerApellido=PrimerApellido.substring(0,3)

        var CodigoRandom : Int
        CodigoRandom= Random().nextInt(1..100)

        UserNameGenerado=PrimerNombre+PrimerApellido+CodigoRandom.toString()

        UsuarioGenerado.text=UserNameGenerado
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }
}
