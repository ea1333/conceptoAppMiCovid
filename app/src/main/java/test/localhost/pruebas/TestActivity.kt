package test.localhost.pruebas

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val actionBar = supportActionBar
        actionBar!!.title = "Detalle del test"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val idTest = intent.extras?.getInt("idTest")
        val db = DatabaseHelper(this)
        val datosTest = db.obtenerTestPorId(idTest)
        val fechaDB = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val fechaNormal = SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy',' H:mm:ss")
        val tvDetalleTestFecha = findViewById<TextView>(R.id.tvDetalleTestFecha).apply {
            text = fechaNormal.format(fechaDB.parse(datosTest.fecha))
        }
        val tvDetalleTestResultado = findViewById<TextView>(R.id.tvDetalleTestResultado).apply {
            text = when (datosTest.resultado) {
                "P" -> "Posiblemente positivo"
                "N" -> "Posiblemente negativo"
                else -> "No disponible"
            }
        }
        val tvDetalleTestCiudadCasos = findViewById<TextView>(R.id.tvDetalleTestCiudadCasos).apply {
            text = if (datosTest.viajo == "true") "Sí" else "No"
        }
        val tvDetalleTestOtraPersona = findViewById<TextView>(R.id.tvDetalleTestOtraPersona).apply {
            text = if (datosTest.estuvoConOtraPersona == "true") "Sí" else "No"
        }
        val tvDetalleTestFiebre = findViewById<TextView>(R.id.tvDetalleTestFiebre).apply {
            text = if (datosTest.fiebre == "true") "Sí" else "No"
        }
        val tvDetalleTestDolorCabeza = findViewById<TextView>(R.id.tvDetalleTestDolorCabeza).apply {
            text = if (datosTest.dolorDeCabeza == "true") "Sí" else "No"
        }
        val tvDetalleTestTos = findViewById<TextView>(R.id.tvDetalleTestTos).apply {
            text = if (datosTest.tos == "true") "Sí" else "No"
        }
        val tvDetalleTestDolorCuerpo = findViewById<TextView>(R.id.tvDetalleTestDolorCuerpo).apply {
            text = if (datosTest.dolorEnCuerpo == "true") "Sí" else "No"
        }
        val tvDetalleTestOtrasCondiciones = findViewById<TextView>(R.id.tvDetalleTestOtrasCondiciones).apply {
            text = if (datosTest.condicionesPreexistentes == "true") "Sí" else "No"
        }
    }
    override fun onSupportNavigateUp() : Boolean {
        super.onBackPressed()
        return true
    }
}