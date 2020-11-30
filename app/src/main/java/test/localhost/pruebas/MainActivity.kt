package test.localhost.pruebas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

const val EXTRA_DNI = "test.localhost.pruebas.EXTRA_DNI"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = DatabaseHelper(this)
        val tvLista = findViewById<TextView>(R.id.tvLista)
        val data = db.leerUsuarios();
        for (i in 0 until data.size) tvLista.append("${data[i].id}: ${data[i].nombre} ${data[i].apellido}, DNI ${data[i].dni}, Pass: ${data[i].contrasenia} \n")
    }
    fun login(view: View) {
        val campoDNI = findViewById<TextInputEditText>(R.id.loginCampoDNI)
        val campoContrasenia = findViewById<TextInputEditText>(R.id.loginCampoContrasenia)
        val valorCampoDNI = Integer.parseInt(campoDNI.text.toString())
        val valorCampoContrasenia = campoContrasenia.text.toString()
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra(EXTRA_DNI, Integer.parseInt(campoDNI.text.toString()))
        startActivity(intent)
    }
    fun registrarForm(view: View) {
        val intentReg = Intent(this, RegistrarActivity::class.java)
        startActivity(intentReg)
    }
}