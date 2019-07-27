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
import java.util.*

class Agregar_Vehiculo : AppCompatActivity() {

    lateinit var PrimerNombre:String
    lateinit var PrimerApellido:String
    lateinit var SegundoApellido:String

    var idUsuario :Int=0

    lateinit var ED_Marca:EditText
    lateinit var ED_Color:EditText
    lateinit var ED_Caracteristicas:EditText
    lateinit var ED_MatriculaQR:TextView
    lateinit var generarMatricula:Button

    lateinit var MatriculaGenerada:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar__vehiculo)
        ED_Marca=findViewById(R.id.ET_Marca)
        ED_Color=findViewById(R.id.ET_Color)
        ED_Caracteristicas=findViewById(R.id.ET_Caracteristicas)
        ED_MatriculaQR=findViewById(R.id.ET_MatriculaQR)
        generarMatricula=findViewById(R.id.BTTN_GenerarMatricula)

        recibirDatos()

    }


    fun recibirDatos(){
        var leer:Bundle=getIntent().extras
        idUsuario=leer.getInt("idUsuario")

        Toast.makeText(this,"$idUsuario", Toast.LENGTH_SHORT).show()
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }


    fun generarMatriculaQR(v:View){

        val queue = Volley.newRequestQueue(this)

        //Toast.makeText(this,usuario+" "+ password,Toast.LENGTH_SHORT).show()
        var url="http://mgf.jpsistemas.solutions/autentificarIDVehiculo.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj: JSONObject = JSONObject(response)
                    if (jsonObj.getString("error")=="false"){

                        PrimerNombre=jsonObj.getString("primerNombre").toString()
                        PrimerApellido=jsonObj.getString("primerApellido").toString()
                        SegundoApellido=jsonObj.getString("segundoApellido").toString()

                        PrimerNombre=PrimerNombre.substring(0..2)
                        PrimerApellido=PrimerApellido.substring(0..2)
                        SegundoApellido=SegundoApellido.substring(0..2)

                        var CodigoRandom : Int
                        CodigoRandom= Random().nextInt(1..100)

                        var MatriculaGenerada:String=PrimerNombre+PrimerApellido+SegundoApellido+CodigoRandom.toString()
                        MatriculaGenerada=MatriculaGenerada
                        Toast.makeText(this,"$MatriculaGenerada",Toast.LENGTH_SHORT).show()
                        ED_MatriculaQR.text=MatriculaGenerada
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
                params.put("idUsuario", idUsuario.toString())
                return params
            }
        }
        queue.add(StringReq)
    }

    fun registrarVehiculo(v:View){
        val queue= Volley.newRequestQueue(this)

        val idUsuarioListo=idUsuario
        val MarcaListo=ED_Marca.text.toString()
        val ColorListo=ED_Color.text.toString()
        val CaracteristicasListo=ED_Caracteristicas.text.toString()
        //val MatriculaQR:String=MatriculaGenerada.toString()


        var url="http://mgf.jpsistemas.solutions/insertarVehiculo.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj = JSONObject(response)
                    Toast.makeText(this,jsonObj.getString("mensaje"),Toast.LENGTH_SHORT).show()

                    finish()


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
                params.put("idUsuario", idUsuarioListo.toString())
                params.put("Marca", MarcaListo)
                params.put("Color", ColorListo)
                params.put("Caracteristicas", CaracteristicasListo)
                params.put("MatriculaQR",ED_MatriculaQR.text.toString() )
                return params
            }
        }
        queue.add(StringReq)
    }

    }

