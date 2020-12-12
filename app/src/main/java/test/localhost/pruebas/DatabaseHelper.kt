package test.localhost.pruebas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(var contexto: Context) : SQLiteOpenHelper(contexto, "www", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTablaUsuarios = "CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR(255)," +
                "apellido VARCHAR(255)," +
                "dni INTEGER," +
                "contrasenia VARCHAR(255)," +
                "genero VARCHAR(255)," +
                "fechaNacimiento VARCHAR(255)," +
                "provincia VARCHAR(255)," +
                "ciudad VARCHAR(255)" +
                ");"
        db?.execSQL(crearTablaUsuarios)
        val crearTablaTests = "CREATE TABLE tests (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha VARCHAR(255)," +
                "resultado VARCHAR(255)," +
                "idUsuario INTEGER," +
                "viajo VARCHAR(255)," +
                "estuvoConOtraPersona VARCHAR(255)," +
                "fiebre VARCHAR(255)," +
                "dolorDeCabeza VARCHAR(255)," +
                "tos VARCHAR(255)," +
                "dolorEnCuerpo VARCHAR(255)," +
                "condicionesPreexistentes VARCHAR(255)" +
                ");"
        db?.execSQL(crearTablaTests)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS tests")
        db.execSQL("DROP TABLE IF EXISTS resultados")
        onCreate(db)
    }
    fun insertarUsuario(usuario: Usuario) {
        val db = this.writableDatabase
        val contenidoValores = ContentValues().also {
            it.put("nombre", usuario.nombre)
            it.put("apellido", usuario.apellido)
            it.put("dni", usuario.dni)
            it.put("contrasenia", usuario.contrasenia)
            it.put("genero", usuario.genero)
            it.put("fechaNacimiento", usuario.fechaNacimiento)
            it.put("provincia", usuario.provincia)
            it.put("ciudad", usuario.ciudad)
        }
        val result = db.insert("usuarios", null, contenidoValores)
        if (result == (0).toLong()) {
            Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(contexto, "Éxito", Toast.LENGTH_SHORT).show()
        }
    }
    fun modificarUsuario(usuario: Usuario) {
        val db = this.writableDatabase
        val contenidoValores = ContentValues().also {
            it.put("nombre", usuario.nombre)
            it.put("apellido", usuario.apellido)
            it.put("dni", usuario.dni)
            it.put("contrasenia", usuario.contrasenia)
            it.put("genero", usuario.genero)
            it.put("fechaNacimiento", usuario.fechaNacimiento)
            it.put("provincia", usuario.provincia)
            it.put("ciudad", usuario.ciudad)
        }
        val result = db.update("usuarios", contenidoValores, "id = ${usuario.id}", null)
        if (result >= 1) {
            Toast.makeText(contexto, "Éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }
    fun leerUsuarios(): MutableList<Usuario> {
        val lista: MutableList<Usuario> = ArrayList()
        val db = this.readableDatabase
        val consulta = "SELECT * FROM usuarios"
        val resultado = db.rawQuery(consulta, null)
        if (resultado.moveToFirst()) {
            do {
                val usuario = Usuario()
                usuario.id = resultado.getInt(resultado.getColumnIndex("id"))
                usuario.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                usuario.apellido = resultado.getString(resultado.getColumnIndex("apellido"))
                usuario.dni = resultado.getInt(resultado.getColumnIndex("dni"))
                usuario.contrasenia = resultado.getString(resultado.getColumnIndex("contrasenia"))
                usuario.provincia = resultado.getString(resultado.getColumnIndex("provincia"))
                lista.add(usuario)
            } while (resultado.moveToNext())
        }
        return lista
    }
    fun obtenerIdUsuarioPorDNI(DNI: Int): Int {
        val db = this.readableDatabase
        val consulta = "SELECT * FROM usuarios WHERE dni = $DNI"
        val resultado = db.rawQuery(consulta, null)
        var retornar = 0
        if (resultado != null) {
            resultado.moveToFirst()
            retornar = Integer.parseInt(resultado.getString(resultado.getColumnIndex("id")))
        }
        return retornar
    }
    fun obtenerDatosUsuarioPorId(id: Int?): Usuario {
        val db = this.readableDatabase
        val consulta = "SELECT * FROM usuarios WHERE id = $id"
        val resultado = db.rawQuery(consulta, null)
        val usuario = Usuario()
        if (resultado != null) {
            resultado.moveToFirst()
            usuario.id = Integer.parseInt(resultado.getString(resultado.getColumnIndex("id")))
            usuario.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
            usuario.apellido = resultado.getString(resultado.getColumnIndex("apellido"))
            usuario.dni = Integer.parseInt(resultado.getString(resultado.getColumnIndex("dni")))
            usuario.contrasenia = resultado.getString(resultado.getColumnIndex("contrasenia"))
            usuario.genero = resultado.getString(resultado.getColumnIndex("genero"))
            usuario.fechaNacimiento = resultado.getString(resultado.getColumnIndex("fechaNacimiento"))
            usuario.provincia = resultado.getString(resultado.getColumnIndex("provincia"))
            usuario.ciudad = resultado.getString(resultado.getColumnIndex("ciudad"))
        }
        return usuario
    }
    fun crearTest(test: Test) {
        val db = this.writableDatabase
        val contenidoValores = ContentValues().also {
            it.put("fecha", test.fecha)
            it.put("resultado", test.resultado)
            it.put("idUsuario", test.idUsuario)
            it.put("viajo", test.viajo)
            it.put("estuvoConOtraPersona", test.estuvoConOtraPersona)
            it.put("fiebre", test.fiebre)
            it.put("dolorDeCabeza", test.dolorDeCabeza)
            it.put("tos", test.tos)
            it.put("dolorEnCuerpo", test.dolorEnCuerpo)
            it.put("condicionesPreexistentes", test.condicionesPreexistentes)
        }
        val result = db.insert("tests", null, contenidoValores)
        if (result == (0).toLong()) {
            Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(contexto, "Éxito", Toast.LENGTH_SHORT).show()
        }
    }
    fun leerTestsPorIdUsuario(id: Int?): MutableList<Test> {
        val lista: MutableList<Test> = ArrayList()
        val db = this.readableDatabase
        val consulta = "SELECT * FROM tests WHERE idUsuario = $id ORDER BY fecha DESC"
        val result = db.rawQuery(consulta, null)
        if (result.moveToFirst()) {
            do {
                val t = Test()
                t.id = result.getInt(result.getColumnIndex("id"))
                t.fecha = result.getString(result.getColumnIndex("fecha"))
                t.resultado = result.getString(result.getColumnIndex("resultado"))
                t.idUsuario = result.getInt(result.getColumnIndex("idUsuario"))
                t.viajo = result.getString(result.getColumnIndex("viajo"))
                t.estuvoConOtraPersona = result.getString(result.getColumnIndex("estuvoConOtraPersona"))
                t.fiebre = result.getString(result.getColumnIndex("fiebre"))
                t.dolorDeCabeza = result.getString(result.getColumnIndex("dolorDeCabeza"))
                t.tos = result.getString(result.getColumnIndex("tos"))
                t.dolorEnCuerpo = result.getString(result.getColumnIndex("dolorEnCuerpo"))
                t.condicionesPreexistentes = result.getString(result.getColumnIndex("condicionesPreexistentes"))
                lista.add(t)
            } while (result.moveToNext())
        }
        return lista
    }
    fun obtenerTestPorId(idUsuario: Int?): Test {
        val db = this.readableDatabase
        val consulta = "SELECT * FROM tests WHERE id = $idUsuario"
        val result = db.rawQuery(consulta, null)
        val t = Test()
        if (result != null) {
            result.moveToFirst()
            t.id = Integer.parseInt(result.getString(result.getColumnIndex("id")))
            t.fecha = result.getString(result.getColumnIndex("fecha"))
            t.resultado = result.getString(result.getColumnIndex("resultado"))
            t.idUsuario = Integer.parseInt(result.getString(result.getColumnIndex("idUsuario")))
            t.viajo = result.getString(result.getColumnIndex("viajo"))
            t.estuvoConOtraPersona = result.getString(result.getColumnIndex("estuvoConOtraPersona"))
            t.fiebre = result.getString(result.getColumnIndex("fiebre"))
            t.dolorDeCabeza = result.getString(result.getColumnIndex("dolorDeCabeza"))
            t.tos = result.getString(result.getColumnIndex("tos"))
            t.dolorEnCuerpo = result.getString(result.getColumnIndex("dolorEnCuerpo"))
            t.condicionesPreexistentes = result.getString(result.getColumnIndex("condicionesPreexistentes"))
        }
        return t
    }
    fun obtenerTestMasRecientePorIdUsuario(idUsuario: Int?): Test {
        val db = this.readableDatabase
        val consulta = "SELECT * FROM tests WHERE idUsuario = $idUsuario ORDER BY fecha DESC"
        val result = db.rawQuery(consulta, null)
        val t = Test()
        if (result != null) {
            result.moveToFirst()
            t.id = Integer.parseInt(result.getString(result.getColumnIndex("id")))
            t.fecha = result.getString(result.getColumnIndex("fecha"))
            t.resultado = result.getString(result.getColumnIndex("resultado"))
            t.idUsuario = Integer.parseInt(result.getString(result.getColumnIndex("idUsuario")))
            t.viajo = result.getString(result.getColumnIndex("viajo"))
            t.estuvoConOtraPersona = result.getString(result.getColumnIndex("estuvoConOtraPersona"))
            t.fiebre = result.getString(result.getColumnIndex("fiebre"))
            t.dolorDeCabeza = result.getString(result.getColumnIndex("dolorDeCabeza"))
            t.tos = result.getString(result.getColumnIndex("tos"))
            t.dolorEnCuerpo = result.getString(result.getColumnIndex("dolorEnCuerpo"))
            t.condicionesPreexistentes = result.getString(result.getColumnIndex("condicionesPreexistentes"))
        }
        return t
    }
}