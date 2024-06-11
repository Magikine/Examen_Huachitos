package marjorie.moya.huachitos.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import marjorie.moya.huachitos.R
import marjorie.moya.huachitos.databinding.ActivityMainBinding
import marjorie.moya.huachitos.view.adapter.CachorrosAdapter
import marjorie.moya.huachitos.viewmodel.CachorrosViewModel

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
         * Configuraciones
         */
        //Configurar el ViewModel
        var viewModelEmpresa = ViewModelProvider(this).get(CachorrosViewModel::class.java)
        //configurando el Loader
        binding.listaCachorros.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        //Configurar el RecyclerView
        binding.listaCachorros.layoutManager = LinearLayoutManager(this)
        //Configurar el Adapter
        var adaptadorCachorros = CachorrosAdapter(listOf())
        binding.listaCachorros.adapter = adaptadorCachorros
        //Configurar el Observador
        viewModelEmpresa.listaCachorros.observe(
            this
        ) { datosCachorros ->
            adaptadorCachorros = CachorrosAdapter(datosCachorros)
            binding.listaCachorros.adapter = adaptadorCachorros
            binding.listaCachorros.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            //Vamos a configurar el Spinner
            val arrayAdapter: ArrayAdapter<*> =
                ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, datosCachorros)
            binding.spinnerCachorros.adapter = arrayAdapter
            binding.spinnerCachorros.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        // You can define your actions as you want
                    }

                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long
                    ) {
                        val selectedObject = datosCachorros.get(position)
                        Toast.makeText(
                            this@MainActivity,
                            "ID: ${selectedObject.id} Name: ${selectedObject.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }
    }
}