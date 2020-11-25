package test.localhost.pruebas

import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_tests.*

private lateinit var linearLayoutManager: LinearLayoutManager

class TestsActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)
        val contexto = this
        val rvListaTests : RecyclerView = findViewById(R.id.rvListaTests)
        val linearLayoutManager = LinearLayoutManager(this)
        rvListaTests.layoutManager = linearLayoutManager
        rvListaTests.setHasFixedSize(true)
        val idUsuario = intent.extras?.getInt("EXTRA_IDUSUARIO")
        db = DatabaseHelper(contexto)
        val data = db.leerTestsPorIdUsuario(idUsuario);
        if (data.size > 0) {
            rvListaTests.visibility = View.VISIBLE
            val mAdapter = TestsAdapter(this, data)
            rvListaTests.adapter = mAdapter
        }
        else {
            rvListaTests.visibility = View.GONE
            Toast.makeText(
                this,
                "No hay ningún test",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}