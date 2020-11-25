package test.localhost.pruebas

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TestsViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
    var rvListaTestsFecha: TextView = vista.findViewById(R.id.rvListaTestsFecha)
    var rvListaTestsResultado: TextView = vista.findViewById(R.id.rvListaTestsResultado)
    var rvListaTestsLink: ConstraintLayout = vista.findViewById(R.id.rvListaTestsLink)
}