package perez.manuel.examen2_perezdominguez

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaContactos : AppCompatActivity() {
    var adapter : VistaContactoAdapter? = null
    var VistaContacto = ArrayList<VistaContacto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_contactos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val boton = findViewById<Button>(R.id.Agregar)
        val list = findViewById<ListView>(R.id.Listview)


        boton.setOnClickListener(){
            val intent = Intent(this, AgregarContacto::class.java)
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_CONTACTO)
        }


        list.setOnItemClickListener { parent, view, position, id ->
            val contactoSeleccionado = VistaContacto[position]

            val intent = Intent(this, PerfilContacto::class.java)

            intent.putExtra("nombre", contactoSeleccionado.Nombre)
            intent.putExtra("ocupacion", contactoSeleccionado.Ocupacion)
            val contacto = VistaContacto[position]

            intent.putExtra("nombre", contacto.Nombre)
            intent.putExtra("ocupacion", contacto.Ocupacion)
            intent.putExtra("email", contacto.Email)
            intent.putExtra("numero", contacto.Numero)
            startActivity(intent)
            startActivity(intent)
        }

        crearContacto()
        adapter = VistaContactoAdapter(this, VistaContacto, this::eliminarContacto)
        list.adapter = adapter


        val bundle = intent.extras
        if (bundle != null) {
            val nombre = bundle.getString("nombre")
            val email = bundle.getString("email")
            val numero = bundle.getString("numero")
            val ocupacion = bundle.getString("ocupacion")

            if (nombre != null && email != null && numero != null && ocupacion != null) {

                VistaContacto.add(VistaContacto(nombre, ocupacion, email, numero))
                adapter?.notifyDataSetChanged()
            }
        }
    }

    val REQUEST_CODE_AGREGAR_CONTACTO = 1

    fun crearContacto(){
        VistaContacto.add(VistaContacto("Juan", "Gameza", "si.com.mx", "999999"))
        VistaContacto.add(VistaContacto("Octavio", "Emprendedor", "no.com.mx", "888888"))
        VistaContacto.add(VistaContacto("Manuel", "Sabritas", "siYno.com.mx", "7777777"))
        VistaContacto.add(VistaContacto("Juana", "ricolino", "noYsi.com.mx", "666666"))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_AGREGAR_CONTACTO && resultCode == RESULT_OK) {
            val nombre = data?.getStringExtra("nombre")
            val email = data?.getStringExtra("email")
            val numero = data?.getStringExtra("numero")
            val ocupacion = data?.getStringExtra("ocupacion")

            if (nombre != null && email != null && numero != null && ocupacion != null) {

                VistaContacto.add(VistaContacto(nombre, ocupacion, email, numero))
                adapter?.notifyDataSetChanged()
            }
        }
    }

    fun eliminarContacto(position: Int) {
        VistaContacto.removeAt(position)
        adapter?.notifyDataSetChanged()
    }

    class VistaContactoAdapter(private val context: Context, private val vistaRepartidor: ArrayList<VistaContacto>, private val eliminarContacto: (Int) -> Unit) : BaseAdapter(){
        override fun getCount(): Int {
            return vistaRepartidor.size
        }

        override fun getItem(position: Int): Any {
            return vistaRepartidor[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.activity_vista_contacto, null)
            val contacto = getItem(position) as VistaContacto

            val nombre = view.findViewById<TextView>(R.id.Nombre)
            val ocupacion = view.findViewById<TextView>(R.id.Ocupacion)
            val email = view.findViewById<TextView>(R.id.Email)
            val numero = view.findViewById<TextView>(R.id.Numero)
            val eliminar = view.findViewById<ImageView>(R.id.imagen)

            nombre.text = contacto.Nombre
            ocupacion.text = contacto.Ocupacion
            email.text = contacto.Email
            numero.text = contacto.Numero


            eliminar.setOnClickListener {
                eliminarContacto(position)
            }

            return view
        }

    }



}