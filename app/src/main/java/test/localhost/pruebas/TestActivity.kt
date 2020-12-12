package test.localhost.pruebas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class TestActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    private lateinit var tvDetalleTestFecha: TextView
    private lateinit var tvDetalleTestResultado: TextView
    private lateinit var tvDetalleTestCiudadCasos: TextView
    private lateinit var tvDetalleTestOtraPersona: TextView
    private lateinit var tvDetalleTestFiebre: TextView
    private lateinit var tvDetalleTestDolorCabeza: TextView
    private lateinit var tvDetalleTestTos: TextView
    private lateinit var tvDetalleTestDolorCuerpo: TextView
    private lateinit var tvDetalleTestOtrasCondiciones: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tvDetalleTestFecha = findViewById(R.id.tvDetalleTestFecha)
        tvDetalleTestResultado = findViewById(R.id.tvDetalleTestResultado)
        tvDetalleTestCiudadCasos = findViewById(R.id.tvDetalleTestCiudadCasos)
        tvDetalleTestOtraPersona = findViewById(R.id.tvDetalleTestOtraPersona)
        tvDetalleTestFiebre = findViewById(R.id.tvDetalleTestFiebre)
        tvDetalleTestDolorCabeza = findViewById(R.id.tvDetalleTestDolorCabeza)
        tvDetalleTestTos = findViewById(R.id.tvDetalleTestTos)
        tvDetalleTestDolorCuerpo = findViewById(R.id.tvDetalleTestDolorCuerpo)
        tvDetalleTestOtrasCondiciones = findViewById(R.id.tvDetalleTestOtrasCondiciones)
        val actionBar = supportActionBar
        actionBar!!.title = "Detalle del test"
        actionBar.setDisplayHomeAsUpEnabled(true)
        val idTest = intent.extras?.getInt("idTest")
        db = DatabaseHelper(this)
        val datosTest = db.obtenerTestPorId(idTest)
        val fechaDB = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val fechaNormal = SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy',' H:mm:ss")
        tvDetalleTestFecha.apply {
            text = fechaNormal.format(fechaDB.parse(datosTest.fecha))
        }
        tvDetalleTestResultado.apply {
            text = when (datosTest.resultado) {
                "P" -> "Posiblemente positivo"
                "N" -> "Posiblemente negativo"
                else -> "No disponible"
            }
        }
        tvDetalleTestCiudadCasos.apply {
            text = if (datosTest.viajo == "true") "Sí" else "No"
        }
        tvDetalleTestOtraPersona.apply {
            text = if (datosTest.estuvoConOtraPersona == "true") "Sí" else "No"
        }
        tvDetalleTestFiebre.apply {
            text = if (datosTest.fiebre == "true") "Sí" else "No"
        }
        tvDetalleTestDolorCabeza.apply {
            text = if (datosTest.dolorDeCabeza == "true") "Sí" else "No"
        }
        tvDetalleTestTos.apply {
            text = if (datosTest.tos == "true") "Sí" else "No"
        }
        tvDetalleTestDolorCuerpo.apply {
            text = if (datosTest.dolorEnCuerpo == "true") "Sí" else "No"
        }
        tvDetalleTestOtrasCondiciones.apply {
            text = if (datosTest.condicionesPreexistentes == "true") "Sí" else "No"
        }
    }
    override fun onSupportNavigateUp() : Boolean {
        super.onBackPressed()
        return true
    }
}