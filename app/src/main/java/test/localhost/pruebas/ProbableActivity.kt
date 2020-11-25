package test.localhost.pruebas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProbableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_probable)

        val contexto = this
        val idUsuario = intent.extras?.getInt("EXTRA_IDUSUARIO")
        Toast.makeText(contexto, idUsuario.toString(), Toast.LENGTH_SHORT).show()
    }
}