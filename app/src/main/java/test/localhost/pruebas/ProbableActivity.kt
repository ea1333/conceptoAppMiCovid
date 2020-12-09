package test.localhost.pruebas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProbableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_probable)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp() : Boolean {
        super.onBackPressed()
        return true
    }
}