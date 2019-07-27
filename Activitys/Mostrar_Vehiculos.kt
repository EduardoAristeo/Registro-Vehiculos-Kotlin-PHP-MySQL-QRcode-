package com.example.developerlalo.gestveih

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray
import Frutas
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_mostrar__vehiculos.*
import java.nio.file.Files.size
import net.glxn.qrgen.android.QRCode;
import android.graphics.Bitmap
import android.widget.ImageView


class Mostrar_Vehiculos : AppCompatActivity() {

    var idUsuario:Int=0
    var list = ArrayList<String>()
    lateinit var Spinner:Spinner
    lateinit var texto:String

    lateinit var imagenCodigo:ImageView

    //var bitmap = QRCode.from(texto).withSize(259,240).bitmap()
    //imagenCodigo.setImageBitmap(bitmap);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar__vehiculos)
        Spinner=findViewById(R.id.Spinner)
        imagenCodigo=findViewById(R.id.CodigoQRimg)
        recibirDatos()
        cargarSpinner()
    }

    fun recibirDatos(){
        var leer:Bundle=getIntent().extras
        idUsuario=leer.getInt("idUsuario")

        Toast.makeText(this,"$idUsuario", Toast.LENGTH_SHORT).show()
    }

    fun cargarSpinner(){

        val queue = Volley.newRequestQueue(this)
        val usuario=idUsuario

        //Toast.makeText(this,usuario+" "+ password,Toast.LENGTH_SHORT).show()
        var url="http://mgf.jpsistemas.solutions/listar.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj: JSONObject = JSONObject(response)
                    /*
                    Toast.makeText(this,jsonObj.getString("frutas"), Toast.LENGTH_SHORT).show()
                    */
                    var contador : Int=0
                    val frutas:JSONArray = jsonObj.getJSONArray("frutas");

                    while (contador<frutas.length()){
                        list.add(frutas[contador].toString())
                        contador++
                    }

                    val spinnerArrayAdapter= ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
                    Spinner!!.setAdapter(spinnerArrayAdapter)
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
                params.put("usuario",usuario.toString() )

                return params
            }
        }
        queue.add(StringReq)

    }

    fun TraerDatos(v:View){
        val queue = Volley.newRequestQueue(this)
        var matricula:String=Spinner.selectedItem.toString()


        //Toast.makeText(this,usuario+" "+ password,Toast.LENGTH_SHORT).show()
        var url="http://mgf.jpsistemas.solutions/DatosVehiculo.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj: JSONObject = JSONObject(response)
                    Toast.makeText(this,jsonObj.getString("mensaje"), Toast.LENGTH_SHORT).show()
                    if (jsonObj.getString("error")=="false"){


                        Marca.text=jsonObj.getString("marca")
                        Color.text=jsonObj.getString("color")
                        Caracteristicas.text=jsonObj.getString("caracteristicas")
                        texto=Spinner.getSelectedItem().toString()
                        var bitmap = QRCode.from(texto).withSize(259,240).bitmap()
                        imagenCodigo.setImageBitmap(bitmap);
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
                params.put("matriculaQR",Spinner.selectedItem.toString() )

                return params
            }
        }
        queue.add(StringReq)
    }

/*
texto=Spinner.getSelectedItem().toString()

                    imagenCodigo.setImageBitmap(bitmap);
 */

}
