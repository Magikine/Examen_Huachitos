package marjorie.moya.huachitos.view

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.recyclerview.widget.LinearLayoutManager
import marjorie.moya.huachitos.R
import marjorie.moya.huachitos.databinding.ActivityMainBinding
import marjorie.moya.huachitos.view.adapter.AnimalAdapter
import marjorie.moya.huachitos.viewmodel.AnimalViewModel


class MainActivity : AppCompatActivity() {
    //Variable de Binding
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuraciones de Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Configurar vista
        setContentView(binding.root)
        /**
         * Configuraciones  .
         */
        //Configurar el ViewModel
        var viewModelAnimal = ViewModelProvider(this).get(AnimalViewModel::class.java)
        //configurando el Loader
        binding.listaAnimal.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        //Configurar el RecyclerView
        binding.listaAnimal.layoutManager = LinearLayoutManager(this)
        //Configurar el Adapter
        var adaptadorAnimal = AnimalAdapter(listOf())
        binding.listaAnimal.adapter = adaptadorAnimal
        //Configurar el Observador
        viewModelAnimal.listaAnimales.observe(
            this
        ) { datosAnimal ->
            adaptadorAnimal = AnimalAdapter(datosAnimal)
            binding.listaAnimal.adapter = adaptadorAnimal
            binding.listaAnimal.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            //Vamos a configurar el Spinner
            val arrayAdapter: ArrayAdapter<*> =
                ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, datosAnimal)
            binding.spinnerAnimal.adapter = arrayAdapter
            binding.spinnerAnimal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        // You can define your actions as you want
                    }

                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long
                    ) {
                        val selectedObject = datosAnimal.get(position)
                        Toast.makeText(
                            this@MainActivity,
                            "ID: ${selectedObject.id} Name: ${selectedObject.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }

viewModelAnimal.errores.observe(this) {
    binding.listaAnimal.visibility = View.GONE
    binding.progressBar.visibility = View.GONE
    val builder = AlertDialog.Builder(this)
    builder.setMessage(it)
        .setPositiveButton("OK") { dialog, id ->
            dialog.dismiss()
        }
    // Create the AlertDialog object and return it.
    builder.create().show()
}
//Ejecucion desde el ViewModel
viewModelAnimal.listarAnimales()
}
}
