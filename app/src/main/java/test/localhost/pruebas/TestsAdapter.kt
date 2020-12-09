package test.localhost.pruebas

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

internal class TestsAdapter(private val context: Context, listaTests: MutableList<Test>, val ultimoTest: Test) : RecyclerView.Adapter<TestsViewHolder>(), Filterable {
    private var listaTests: MutableList<Test> = listaTests
    val fechaDB = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val fechaFormateada = SimpleDateFormat("d/M/yyyy H:mm:ss")
    override fun onBindViewHolder(holder: TestsViewHolder, position: Int) {
        val tests = listaTests[position]
        holder.rvListaTestsFecha.text = "Fecha: " + fechaFormateada.format(fechaDB.parse(tests.fecha))
        holder.rvListaTestsResultado.text = when (tests.resultado) {
            "P" -> "Resultado: Posiblemente positivo"
            "N" -> "Resultado: Posiblemente negativo"
            else -> "Resultado: No disponible"
        }
        holder.rvListaTestsLink.setOnClickListener {
            val intent = Intent(it.context, TestActivity::class.java)
            intent.putExtra("idTest", tests.id)
            it.context.startActivity(intent)
        }
        if (tests.id == ultimoTest.id) {
            holder.rvListaTestsFecha.textSize = 20F
            holder.rvListaTestsResultado.textSize = 20F
        }
    }
    override fun getItemCount(): Int {
        return listaTests.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_tests, parent, false)
        return TestsViewHolder(vista)
    }
    override fun getFilter(): Filter? {
       return null
    }
}