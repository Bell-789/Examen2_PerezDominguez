package perez.manuel.examen2_perezdominguez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)


        val Nombre = findViewById<EditText>(R.id.Nombre)
        val Email = findViewById<EditText>(R.id.Email)
        val Numero = findViewById<EditText>(R.id.Numero)
        val Ocupacion = findViewById<EditText>(R.id.Ocupacion)
        val Guardar = findViewById<Button>(R.id.btnGuardar)


        Guardar.setOnClickListener {
            val nombre = Nombre.text.toString()
            val email = Email.text.toString()
            val numero = Numero.text.toString()
            val ocupacion = Ocupacion.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && numero.isNotEmpty() && ocupacion.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra("nombre", nombre)
                intent.putExtra("email", email)
                intent.putExtra("numero", numero)
                intent.putExtra("ocupacion", ocupacion)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }
}