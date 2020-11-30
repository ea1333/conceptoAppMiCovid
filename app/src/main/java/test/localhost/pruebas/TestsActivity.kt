package test.localhost.pruebas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TestsActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)
        val actionBar = supportActionBar
        actionBar!!.title = "Lista de tests"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val rvListaTests : RecyclerView = findViewById(R.id.rvListaTests)
        val linearLayoutManager = LinearLayoutManager(this)
        rvListaTests.layoutManager = linearLayoutManager
        rvListaTests.setHasFixedSize(true)
        val idUsuario = intent.extras?.getInt(EXTRA_IDUSUARIO)
        db = DatabaseHelper(this)
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
    override fun onSupportNavigateUp() : Boolean {
        super.onBackPressed()
        return true
    }
}