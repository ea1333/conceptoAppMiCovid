package test.localhost.pruebas

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_registrar.*

class ActualizarUsuarioActivity : AppCompatActivity() {
    private lateinit var registroCampoNombre: TextInputEditText
    private lateinit var registroCampoApellido: TextInputEditText
    private lateinit var registroCampoFechaNacimiento: TextInputEditText
    private lateinit var registroCampoDNI: TextInputEditText
    private lateinit var registroCampoContrasenia: TextInputEditText
    private lateinit var registroCampoContraseniaRepetir: TextInputEditText
    private lateinit var registroGeneroF: MaterialRadioButton
    private lateinit var registroGeneroM: MaterialRadioButton
    private lateinit var registroGeneroO: MaterialRadioButton
    private lateinit var spinnerProvincia: Spinner
    private lateinit var spinnerCiudades: Spinner
    private lateinit var registroBtnRegistrar: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        val idUsuario = intent.extras?.getInt(EXTRA_IDUSUARIO)
        db = DatabaseHelper(this)
        val actionBar = supportActionBar
        actionBar!!.title = "Actualizar datos del usuario"
        actionBar.setDisplayHomeAsUpEnabled(true)
        registroCampoNombre = findViewById(R.id.registroCampoNombre)
        registroCampoApellido = findViewById(R.id.registroCampoApellido)
        registroCampoFechaNacimiento = findViewById(R.id.registroCampoFechaNacimiento)
        registroCampoDNI = findViewById(R.id.registroCampoDNI)
        registroCampoContrasenia = findViewById(R.id.registroCampoContrasenia)
        registroCampoContraseniaRepetir = findViewById(R.id.registroCampoContraseniaRepetir)
        registroGeneroF = findViewById(R.id.registroGeneroF)
        registroGeneroM = findViewById(R.id.registroGeneroM)
        registroGeneroO = findViewById(R.id.registroGeneroO)
        spinnerProvincia = findViewById(R.id.registroCampoProvincia)
        spinnerCiudades = findViewById(R.id.registroCampoCiudad)
        registroBtnRegistrar = findViewById(R.id.registroBtnRegistrar)
        val usuarioPorId = db.obtenerDatosUsuarioPorId(idUsuario)

        var usuNombre: String = usuarioPorId.nombre
        registroCampoNombre.setText(usuNombre)
        var usuApellido: String = usuarioPorId.apellido
        registroCampoApellido.setText(usuApellido)
        var usuFechaNacimiento: String = usuarioPorId.fechaNacimiento
        registroCampoFechaNacimiento.setText(usuFechaNacimiento)
        var usuDNI: String = usuarioPorId.dni.toString()
        registroCampoDNI.setText(usuDNI)
        when (usuarioPorId.genero) {
            "M" -> registroGeneroM.isChecked = true
            "F" -> registroGeneroF.isChecked = true
            else -> registroGeneroO.isChecked = true
        }

        val provincias = resources.getStringArray(R.array.provincias)
        spinnerProvincia.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provincias)
        val ciudadesCABA = resources.getStringArray(R.array.ciudadesCABA)
        val ciudadesCABAAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesCABA)
        val ciudadesBsAs = resources.getStringArray(R.array.ciudadesBsAs)
        val ciudadesBsAsAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesBsAs)
        val ciudadesCata = resources.getStringArray(R.array.ciudadesCata)
        val ciudadesCataAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesCata)
        val ciudadesCba = resources.getStringArray(R.array.ciudadesCba)
        val ciudadesCbaAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesCba)
        val ciudadesChb = resources.getStringArray(R.array.ciudadesChb)
        val ciudadesChbAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesChb)
        val ciudadesChc = resources.getStringArray(R.array.ciudadesChc)
        val ciudadesChcAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesChc)
        val ciudadesCorr = resources.getStringArray(R.array.ciudadesCorr)
        val ciudadesCorrAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesCorr)
        val ciudadesER = resources.getStringArray(R.array.ciudadesER)
        val ciudadesERAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesER)
        val ciudadesFor = resources.getStringArray(R.array.ciudadesFor)
        val ciudadesForAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesFor)
        val ciudadesJjy = resources.getStringArray(R.array.ciudadesJjy)
        val ciudadesJjyAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesJjy)
        val ciudadesLP = resources.getStringArray(R.array.ciudadesLP)
        val ciudadesLPAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesLP)
        val ciudadesLR = resources.getStringArray(R.array.ciudadesLR)
        val ciudadesLRAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesLR)
        val ciudadesMza = resources.getStringArray(R.array.ciudadesMza)
        val ciudadesMzaAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesMza)
        val ciudadesMis = resources.getStringArray(R.array.ciudadesMis)
        val ciudadesMisAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesMis)
        val ciudadesNqn = resources.getStringArray(R.array.ciudadesNqn)
        val ciudadesNqnAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesNqn)
        val ciudadesRN = resources.getStringArray(R.array.ciudadesRN)
        val ciudadesRNAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesRN)
        val ciudadesSalta = resources.getStringArray(R.array.ciudadesSalta)
        val ciudadesSaltaAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesSalta)
        val ciudadesSE = resources.getStringArray(R.array.ciudadesSE)
        val ciudadesSEAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesSE)
        val ciudadesSC = resources.getStringArray(R.array.ciudadesSC)
        val ciudadesSCAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesSC)
        val ciudadesSJ = resources.getStringArray(R.array.ciudadesSJ)
        val ciudadesSJAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesSJ)
        val ciudadesSL = resources.getStringArray(R.array.ciudadesSL)
        val ciudadesSLAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesSL)
        val ciudadesSF = resources.getStringArray(R.array.ciudadesSF)
        val ciudadesSFAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesSF)
        val ciudadesTF = resources.getStringArray(R.array.ciudadesTF)
        val ciudadesTFAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesTF)
        val ciudadesTuc = resources.getStringArray(R.array.ciudadesTuc)
        val ciudadesTucAA = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadesTuc)

        when (usuarioPorId.provincia) {
            "Buenos Aires" -> {
                spinnerCiudades.adapter = ciudadesBsAsAA
                spinnerProvincia.setSelection(1)
            }
            "Catamarca" -> {
                spinnerCiudades.adapter = ciudadesCataAA
                spinnerProvincia.setSelection(2)
            }
            "Chaco" -> {
                spinnerCiudades.adapter = ciudadesChcAA
                spinnerProvincia.setSelection(3)
            }
            "Chubut" -> {
                spinnerCiudades.adapter = ciudadesChbAA
                spinnerProvincia.setSelection(4)
            }
            "Ciudad Autónoma de Buenos Aires" -> {
                spinnerCiudades.adapter = ciudadesCABAAA
                spinnerProvincia.setSelection(5)
            }
            "Corrientes" -> {
                spinnerCiudades.adapter = ciudadesCorrAA
                spinnerProvincia.setSelection(6)
            }
            "Córdoba" -> {
                spinnerCiudades.adapter = ciudadesCbaAA
                spinnerProvincia.setSelection(7)
            }
            "Entre Ríos" -> {
                spinnerCiudades.adapter = ciudadesERAA
                spinnerProvincia.setSelection(8)
            }
            "Formosa" -> {
                spinnerCiudades.adapter = ciudadesForAA
                spinnerProvincia.setSelection(9)
            }
            "Jujuy" -> {
                spinnerCiudades.adapter = ciudadesJjyAA
                spinnerProvincia.setSelection(10)
            }
            "La Pampa" -> {
                spinnerCiudades.adapter = ciudadesLPAA
                spinnerProvincia.setSelection(11)
            }
            "La Rioja" -> {
                spinnerCiudades.adapter = ciudadesLRAA
                spinnerProvincia.setSelection(12)
            }
            "Mendoza" -> {
                spinnerCiudades.adapter = ciudadesMzaAA
                spinnerProvincia.setSelection(13)
            }
            "Misiones" -> {
                spinnerCiudades.adapter = ciudadesMisAA
                spinnerProvincia.setSelection(14)
            }
            "Neuquén" -> {
                spinnerCiudades.adapter = ciudadesNqnAA
                spinnerProvincia.setSelection(15)
            }
            "Río Negro" -> {
                spinnerCiudades.adapter = ciudadesRNAA
                spinnerProvincia.setSelection(16)
            }
            "Salta" -> {
                spinnerCiudades.adapter = ciudadesSaltaAA
                spinnerProvincia.setSelection(17)
            }
            "San Juan" -> {
                spinnerCiudades.adapter = ciudadesSJAA
                spinnerProvincia.setSelection(18)
            }
            "San Luis" -> {
                spinnerCiudades.adapter = ciudadesSLAA
                spinnerProvincia.setSelection(19)
            }
            "Santa Cruz" -> {
                spinnerCiudades.adapter = ciudadesSCAA
                spinnerProvincia.setSelection(20)
            }
            "Santa Fe" -> {
                spinnerCiudades.adapter = ciudadesSFAA
                spinnerProvincia.setSelection(21)
            }
            "Santiago del Estero" -> {
                spinnerCiudades.adapter = ciudadesSEAA
                spinnerProvincia.setSelection(22)
            }
            "Tierra del Fuego, Antártida e Islas del Atlántico Sur" -> {
                spinnerCiudades.adapter = ciudadesTFAA
                spinnerProvincia.setSelection(23)
            }
            "Tucumán" -> {
                spinnerCiudades.adapter = ciudadesTucAA
                spinnerProvincia.setSelection(24)
            }
        }

        spinnerProvincia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (spinnerProvincia.selectedItem) {
                    "Buenos Aires" -> spinnerCiudades.adapter = ciudadesBsAsAA
                    "Catamarca" -> spinnerCiudades.adapter = ciudadesCataAA
                    "Chaco" -> spinnerCiudades.adapter = ciudadesChcAA
                    "Chubut" -> spinnerCiudades.adapter = ciudadesChbAA
                    "Ciudad Autónoma de Buenos Aires" -> spinnerCiudades.adapter = ciudadesCABAAA
                    "Corrientes" -> spinnerCiudades.adapter = ciudadesCorrAA
                    "Córdoba" -> spinnerCiudades.adapter = ciudadesCbaAA
                    "Entre Ríos" -> spinnerCiudades.adapter = ciudadesERAA
                    "Formosa" -> spinnerCiudades.adapter = ciudadesForAA
                    "Jujuy" -> spinnerCiudades.adapter = ciudadesJjyAA
                    "La Pampa" -> spinnerCiudades.adapter = ciudadesLPAA
                    "La Rioja" -> spinnerCiudades.adapter = ciudadesLRAA
                    "Mendoza" -> spinnerCiudades.adapter = ciudadesMzaAA
                    "Misiones" -> spinnerCiudades.adapter = ciudadesMisAA
                    "Neuquén" -> spinnerCiudades.adapter = ciudadesNqnAA
                    "Río Negro" -> spinnerCiudades.adapter = ciudadesRNAA
                    "Salta" -> spinnerCiudades.adapter = ciudadesSaltaAA
                    "San Juan" -> spinnerCiudades.adapter = ciudadesSJAA
                    "San Luis" -> spinnerCiudades.adapter = ciudadesSLAA
                    "Santa Cruz" -> spinnerCiudades.adapter = ciudadesSCAA
                    "Santa Fe" -> spinnerCiudades.adapter = ciudadesSFAA
                    "Santiago del Estero" -> spinnerCiudades.adapter = ciudadesSEAA
                    "Tierra del Fuego, Antártida e Islas del Atlántico Sur" -> spinnerCiudades.adapter = ciudadesTFAA
                    "Tucumán" -> spinnerCiudades.adapter = ciudadesTucAA
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        registroBtnRegistrar.apply {
            text = "Actualizar"
        }
        registroBtnRegistrar.setOnClickListener {
            val usuario = Usuario()
            usuario.nombre = registroCampoNombre.text.toString()
            usuario.apellido = registroCampoApellido.text.toString()
            usuario.dni = registroCampoDNI.text.toString().toInt()
            usuario.contrasenia = registroCampoContrasenia.text.toString()
            usuario.genero = when {
                registroGeneroF.isChecked -> "F"
                registroGeneroM.isChecked -> "M"
                else -> "O"
            }
            usuario.fechaNacimiento = registroCampoFechaNacimiento.text.toString()
            usuario.provincia = spinnerProvincia.selectedItem.toString()
            usuario.ciudad = spinnerCiudades.selectedItem.toString()
            if (idUsuario != null) usuario.id = idUsuario
            db.modificarUsuario(usuario)
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra(EXTRA_DNI, usuario.dni)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp() : Boolean {
        super.onBackPressed()
        return true
    }
}