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
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_menu__vigilante.*
import kotlinx.android.synthetic.main.activity_mostrar__vehiculos.*
import net.glxn.qrgen.android.QRCode
import org.json.JSONException
import org.json.JSONObject

class Menu_Vigilante : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu__vigilante)
        initFunc()
    }


    private fun initFunc(){
        LectorQR.setOnClickListener {
            initScan()
        }
    }

    private fun initScan(){
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if(result != null){
            if (result.contents == null){
                Toast.makeText(this,"The data is emty", Toast.LENGTH_LONG).show()
            }
            else{
                MatriculaQR.setText(result.contents.toString())

                TraerDatos();







            }
        }else {

            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun TraerDatos(){
        val queue = Volley.newRequestQueue(this)



        //Toast.makeText(this,usuario+" "+ password,Toast.LENGTH_SHORT).show()
        var url="http://mgf.jpsistemas.solutions/DatosVehiculo.php"
        val StringReq = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String>{ response ->
                try {
                    val jsonObj: JSONObject = JSONObject(response)
                    Toast.makeText(this,jsonObj.getString("mensaje"), Toast.LENGTH_SHORT).show()
                    if (jsonObj.getString("error")=="false"){


                        MarcaTXT.text=jsonObj.getString("marca")
                        ColorTXT.text=jsonObj.getString("color")
                        CaracteristicasTXT.text=jsonObj.getString("caracteristicas")
                        PropietarioTXT.text=jsonObj.getString("nombre")+"  "+jsonObj.getString("Apellido")
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
                params.put("matriculaQR", MatriculaQR.text.toString())

                return params
            }
        }
        queue.add(StringReq)
    }

}
