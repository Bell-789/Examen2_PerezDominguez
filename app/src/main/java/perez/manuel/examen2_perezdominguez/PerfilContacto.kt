package perez.manuel.examen2_perezdominguez

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_contacto)


        val nombre = intent.getStringExtra("nombre")
        val ocupacion = intent.getStringExtra("ocupacion")


        val nombreView = findViewById<TextView>(R.id.Nombre)
        val ocupacionView = findViewById<TextView>(R.id.Ocupacion)

        nombreView.text = nombre
        ocupacionView.text = ocupacion

        // Obtener datos enviados desde AgregarContacto
        val nombrePerfil = intent.getStringExtra("nombre")
        val email = intent.getStringExtra("email")
        val numero = intent.getStringExtra("numero")
        val ocupacionPerfil = intent.getStringExtra("ocupacion")


        findViewById<TextView>(R.id.Nombre).text = nombrePerfil
        findViewById<TextView>(R.id.Ocupacion).text = ocupacionPerfil
        findViewById<TextView>(R.id.Gmail).text = email
        findViewById<TextView>(R.id.Numero).text = numero
    }
}