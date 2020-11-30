package test.localhost.pruebas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class OpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        val actionBar = supportActionBar
        actionBar!!.title = "Nuevo test"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val idUsuario = intent.extras?.getInt(EXTRA_IDUSUARIO)
        val calendario = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val swCiudadCasos = findViewById<Switch>(R.id.switchCiudadCasos)
        val swOtraPersona = findViewById<Switch>(R.id.switchOtraPersona)
        val swFiebre = findViewById<Switch>(R.id.switchFiebre)
        val swDolorCabeza = findViewById<Switch>(R.id.switchDolorCabeza)
        val swTos = findViewById<Switch>(R.id.switchTos)
        val swDolorCuerpo = findViewById<Switch>(R.id.switchDolorCuerpo)
        val swOtrasCondiciones = findViewById<Switch>(R.id.switchOtrasCondiciones)
        val botonPreguntasContinuar = findViewById<Button>(R.id.botonPreguntasContinuar)
        botonPreguntasContinuar.setOnClickListener {
            val test = Test()
            test.fecha = sdf.format(calendario.time)
            test.resultado = ""
            if (idUsuario != null) test.idUsuario = idUsuario
            test.viajo = if (swCiudadCasos.isChecked) "true" else "false"
            test.estuvoConOtraPersona = if (swOtraPersona.isChecked) "true" else "false"
            test.fiebre = if (swFiebre.isChecked) "true" else "false"
            test.dolorDeCabeza = if (swDolorCabeza.isChecked) "true" else "false"
            test.tos = if (swTos.isChecked) "true" else "false"
            test.dolorEnCuerpo = if (swDolorCuerpo.isChecked) "true" else "false"
            test.condicionesPreexistentes = if (swOtrasCondiciones.isChecked) "true" else "false"
            val db = DatabaseHelper(this)
            db.crearTest(test)
            if (swCiudadCasos.isChecked || swOtraPersona.isChecked || swFiebre.isChecked || swDolorCabeza.isChecked || swDolorCuerpo.isChecked || swTos.isChecked || swOtrasCondiciones.isChecked) {
                val intentProbable = Intent(this, ProbableActivity::class.java)
                startActivity(intentProbable)
            } else {
                val intentSinSintomas = Intent(this, SinSintomasActivity::class.java)
                startActivity(intentSinSintomas)
            }
        }
    }
    override fun onSupportNavigateUp() : Boolean {
        super.onBackPressed()
        return true
    }
}